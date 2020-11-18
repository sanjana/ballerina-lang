/*
 *  Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package io.ballerina.runtime.types;

import io.ballerina.runtime.api.types.ResourceFunctionType;
import io.ballerina.runtime.api.types.Type;

import java.util.StringJoiner;

/**d
 * {@code ResourceFunction} represents a resource function in Ballerina.
 *
 * @since 2.0
 */
public class BResourceFunctionType extends BMemberFunctionType implements ResourceFunctionType {

    public final String accessor;
    public final String[] resourcePath;
    public final String[] paramNames;

    public BResourceFunctionType(String funcName, BObjectType parent, BFunctionType type, int flags,
                                 String accessor, String[] resourcePath, String[] paramNames) {
        super(funcName, parent, type, flags);
        this.type = type;
        this.flags = flags;
        this.accessor = accessor;
        this.resourcePath = resourcePath;
        this.paramNames = paramNames;
    }

    @Override
    public String toString() {
        StringJoiner rp = new StringJoiner("/");
        for (String p : resourcePath) {
            rp.add(p);
        }
        StringJoiner sj = new StringJoiner(",", "resource function " + accessor + " " + rp.toString() +
                "(", ") returns (" + type.retType + ")");
        Type[] types = type.paramTypes;
        for (int i = 0; i < types.length; i++) {
            Type type = types[i];
            sj.add(type.getName() + " " + paramNames[i]);
        }
        return sj.toString();
    }

    @Override
    public String[] getParamNames() {
        return paramNames;
    }

    @Override
    public String getAccessor() {
        return accessor;
    }

    @Override
    public String[] getResourcePath() {
        return resourcePath;
    }
}