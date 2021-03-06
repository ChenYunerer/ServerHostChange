# ServerHostChanger
An easy way to change android application base url for debugging

## How to use
### Gradle

    implementation 'fun.yuner:serverhostchanger:last-version'

### Maven

    <dependency>
      <groupId>fun.yuner</groupId>
      <artifactId>serverhostchanger</artifactId>
      <version>last-version</version>
      <type>aar</type>
    </dependency>

### API
#### init ServerHostChanger in Application.java
    ServerHostChangeUtil.init(context, BASE_URL);
#### start modify activity
    ServerHostChangeUtil.startChangeServerHostActivity(activity, REQUEST_CODE);
#### get current url
    ServerHostChangeUtil.getCurrentServerHost(content);
#### activity callback
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            //In order for the changes to take effect sometimes you should
            //restart app like : System.exit(0);
        }
    }

### License

    Copyright 2019 ChenYun, Inc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
