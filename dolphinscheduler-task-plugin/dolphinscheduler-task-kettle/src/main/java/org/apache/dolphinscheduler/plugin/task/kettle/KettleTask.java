/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.dolphinscheduler.plugin.task.kettle;

import com.google.common.base.Preconditions;
import org.apache.dolphinscheduler.common.utils.JSONUtils;
import org.apache.dolphinscheduler.plugin.task.api.*;
import org.apache.dolphinscheduler.plugin.task.api.model.TaskResponse;
import org.apache.dolphinscheduler.plugin.task.api.parameters.AbstractParameters;

import java.io.File;

/**
 * kettle task
 */
public class KettleTask extends AbstractTask {

    /**
     * kettle parameters
     */
    protected KettleParameters kettleParameters;

    /**
     * shell command executor
     */
    private ShellCommandExecutor shellCommandExecutor;

    protected TaskExecutionContext taskRequest;


    /**
     * constructor
     *
     * @param taskRequest taskRequest
     */
    public KettleTask(TaskExecutionContext taskRequest) {
        super(taskRequest);
        this.taskRequest = taskRequest;

        this.shellCommandExecutor = new ShellCommandExecutor(this::logHandle,
                taskRequest,
                logger);
    }

    @Override
    public void init() {
        logger.info("kettle task params {}", taskRequest.getTaskParams());

        kettleParameters = JSONUtils.parseObject(taskRequest.getTaskParams(), KettleParameters.class);

        if (!kettleParameters.checkParameters()) {
            throw new TaskException("kettle task params is not valid");
        }
    }

    @Override
    public void handle(TaskCallBack taskCallBack) throws TaskException {
        try {

            String command = buildKettleExecuteCommand(kettleParameters);

            TaskResponse taskResponse = shellCommandExecutor.run(command);
            setExitStatusCode(taskResponse.getExitStatusCode());
            setProcessId(taskResponse.getProcessId());
            setVarPool(shellCommandExecutor.getVarPool());
            kettleParameters.dealOutParam(shellCommandExecutor.getVarPool());
        } catch (Exception e) {
            logger.error("kettle task failure", e);
            setExitStatusCode(TaskConstants.EXIT_CODE_FAILURE);
            throw new TaskException("run kettle task error", e);
        }
    }

    @Override
    public void cancel() throws TaskException {
        // cancel process
        try {
            shellCommandExecutor.cancelApplication();
        } catch (Exception e) {
            throw new TaskException("cancel application error", e);
        }
    }

    @Override
    public AbstractParameters getParameters() {
        return kettleParameters;
    }


    protected String buildKettleExecuteCommand(KettleParameters kettleParameters) {

        Preconditions.checkNotNull(kettleParameters.getKettleStreamType(), "kettle stream type cannot be null");
        Preconditions.checkNotNull(kettleParameters.getName(), "kettle trans or job name cannot be null");
        Preconditions.checkNotNull(kettleParameters.getRep(), "kettle rep cannot be null");
        Preconditions.checkNotNull(kettleParameters.getUser(), "kettle user cannot be null");
        Preconditions.checkNotNull(kettleParameters.getPass(), "kettle pass cannot be null");
        Preconditions.checkNotNull(kettleParameters.getDir(), "kettle dir cannot be null");

        String cmd = "";
        String kettleHome = String.format("${%s}", KettleConstants.KETTLE_HOME);
        if(KettleConstants.TRANS.equals(kettleParameters.getKettleStreamType())){
            cmd = kettleHome+ File.separator+ KettleConstants.PAN_SH+KettleConstants.SEPARATOR
                    +KettleConstants.ARG_SEPARATOR+KettleConstants.TRANS+KettleConstants.EQUAL_SIGN
                    +kettleParameters.getName();
        }else if(KettleConstants.JOB.equals(kettleParameters.getKettleStreamType())){
            cmd = kettleHome+ File.separator+ KettleConstants.KITCHEN_SH+KettleConstants.SEPARATOR
                    +KettleConstants.ARG_SEPARATOR+KettleConstants.JOB+KettleConstants.EQUAL_SIGN
                    +kettleParameters.getName();
        }
        cmd = cmd.concat(KettleConstants.SEPARATOR).concat(KettleConstants.REP).concat(KettleConstants.EQUAL_SIGN).concat(kettleParameters.getRep())
           .concat(KettleConstants.SEPARATOR).concat(KettleConstants.USER).concat(KettleConstants.EQUAL_SIGN).concat(kettleParameters.getUser())
           .concat(KettleConstants.SEPARATOR).concat(KettleConstants.PASS).concat(KettleConstants.EQUAL_SIGN).concat(kettleParameters.getPass())
           .concat(KettleConstants.SEPARATOR).concat(KettleConstants.DIR).concat(KettleConstants.EQUAL_SIGN).concat(kettleParameters.getDir());

        logger.info("kettle command : {}", cmd);
        return cmd;
    }





}
