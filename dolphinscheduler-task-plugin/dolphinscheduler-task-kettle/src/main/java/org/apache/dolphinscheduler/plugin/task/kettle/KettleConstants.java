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

public class KettleConstants {

    private KettleConstants() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * kettle home
     */
    public static final String KETTLE_HOME = "KETTLE_HOME";

    /**
     * transform
     */
    public static final String TRANS = "trans";

    /**
     * job
     */
    public static final String JOB = "job";

    /**
     * separator
     */
    public static final String SEPARATOR = " ";
    /**
     * rep
     */
    public static final String REP = "-rep";
    /**
     * user
     */
    public static final String USER = "-user";
    /**
     * pass
     */
    public static final String PASS = "-pass";
    /**
     * dir
     */
    public static final String DIR = "-dir";


    /**
     * arg separator
     */
    public static final String ARG_SEPARATOR = "-";

    /**
     * EQUAL SIGN
     */
    public static final String EQUAL_SIGN = "=";
    /**
     * pan.sh
     */
    public static final String PAN_SH = "pan.sh";
    /**
     * kitchen.sh
     */
    public static final String KITCHEN_SH = "kitchen.sh";


}
