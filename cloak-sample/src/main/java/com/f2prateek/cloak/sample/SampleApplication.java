/*
 * Copyright 2013 Prateek Srivastava (@f2prateek)
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.f2prateek.cloak.sample;

import com.f2prateek.cloak.CloakedApplication;
import com.squareup.otto.Bus;
import dagger.Module;
import dagger.Provides;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Singleton;

/**
 * Sample Application class.
 * All that is required is a module that lists all the injection points for the apps.
 * This sample goes a bit further to show how you can specify your own dependencies, an event bus in
 * this case.
 */
public class SampleApplication extends CloakedApplication {

  @Override
  protected List<Object> getModules() {
    ArrayList<Object> modules = new ArrayList<Object>(super.getModules());
    modules.add(new SampleModule());
    return modules;
  }

  @Module(
      injects = {
          SampleActivity.class, SampleReceiverFragment.class, SampleSenderFragment.class
      }, complete = false)
  /** App specific module. List all injection points here. */
  public class SampleModule {
    @Provides @Singleton Bus provideBus() {
      return new Bus();
    }
  }
}
