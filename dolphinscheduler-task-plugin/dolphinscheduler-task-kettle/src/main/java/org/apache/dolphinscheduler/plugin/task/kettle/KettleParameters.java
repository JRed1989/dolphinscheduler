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

import org.apache.dolphinscheduler.plugin.task.api.model.ResourceInfo;
import org.apache.dolphinscheduler.plugin.task.api.parameters.AbstractParameters;

import java.util.List;

public class KettleParameters extends AbstractParameters {

    /**
     * kettle流类型.类型为trans或者job.
     * trans: 转换
     * job: 作业
     */
    private String kettleStreamType;
    /**
     * 资源库名称
     */
    private String rep;
    /**
     * 资源库用户名
     */
    private String user;
    /**
     * 资源库密码
     */
    private String pass;
    /**
     * 目录
     */
    private String dir;

    /**
     * 转换名称或者作业名称
     */
    private String name;

    /**
     * resource list
     */
    private List<ResourceInfo> resourceList;

    public String getKettleStreamType() {
        return kettleStreamType;
    }

    public void setKettleStreamType(String kettleStreamType) {
        this.kettleStreamType = kettleStreamType;
    }

    public String getRep() {
        return rep;
    }

    public void setRep(String rep) {
        this.rep = rep;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ResourceInfo> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<ResourceInfo> resourceList) {
        this.resourceList = resourceList;
    }

    @Override
    public boolean checkParameters() {
        return kettleStreamType != null && !kettleStreamType.isEmpty()
                && name != null && !name.isEmpty()
                && rep != null && !rep.isEmpty()
                && user != null && !user.isEmpty()
                && pass != null && !pass.isEmpty()
                && dir != null && !dir.isEmpty()
                ;
    }

    @Override
    public List<ResourceInfo> getResourceFilesList() {
        return this.resourceList;
    }
}
