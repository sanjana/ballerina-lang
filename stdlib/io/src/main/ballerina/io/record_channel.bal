// Copyright (c) 2017 WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
//
// WSO2 Inc. licenses this file to you under the Apache License,
// Version 2.0 (the "License"); you may not use this file except
// in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

# Represents a channel which will allow to read/write records through a given CharacterChannel.
public type DelimitedTextRecordChannel object {
    private CharacterChannel channel;
    private string rs;
    private string fs;

    # Constructs a DelimitedTextRecordChannel from a given CharacterChannel.
    #
    # + channel - CharacterChannel which will point to the input/output resource
    # + rs - Record separator (this could be a regex)
    # + fs - Field separator (this could be a regex)
    public new(channel, fs = "", rs = "", string fmt = "default") {
        init(channel, fs, rs, fmt);
    }

    # Initializes delimited record channel.
    #
    # + characterChannel - Character channel which will be used for reading/writing records
    # + fieldSeparator - Field separator which will separate between fields
    # + recordSeparator - Record separator which will separate between records
    # + fmt - Format which will be used to represent the type of record i.e csv
    native function init(CharacterChannel characterChannel, string fieldSeparator, string recordSeparator, string fmt);

    # Checks whether there's a record left to be read.
    #
    # + return - True if there's a record left to be read
    public native function hasNext() returns boolean;

    # Get next record from the input/output resource.
    #
    # + return - Set of fields included in the record or an error
    public native function getNext() returns @tainted string[]|error;

    # Writes records to a given input/output resource.
    #
    # + textRecord - List of fields to be written
    # + return - An error if the records could not be written properly
    public native function write(string[] textRecord) returns error?;

    # Closes a given record channel.
    #
    # + return - An error if the record channel could not be closed properly
    public native function close() returns error?;
};
