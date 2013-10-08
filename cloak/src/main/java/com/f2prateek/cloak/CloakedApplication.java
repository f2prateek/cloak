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

package com.f2prateek.cloak;

import android.app.Application;
import dagger.ObjectGraph;
import java.util.Arrays;
import java.util.List;

/**
 * The application class you should extend from.
 *
 * To add your own modules, override {@link CloakedApplication#getModules()}, being sure to include
 * all modules provided by {@code super.getModules()}.
 *
 * At the very minimum, you will need to provide a module that lists all inject points for the app.
 */
public class CloakedApplication extends Application {

  private ObjectGraph applicationGraph;

  @Override public void onCreate() {
    super.onCreate();
    applicationGraph = ObjectGraph.create(getModules().toArray());

    // Inject ourselves so subclasses will have dependencies fulfilled when this method returns.
    applicationGraph.inject(this);
  }

  /**
   * A list of modules to use for the application graph. Subclasses can override this method to
   * provide additional modules provided they call {@code super.getModules()}.
   */
  protected List<Object> getModules() {
    return Arrays.<Object>asList(new AndroidModule(this));
  }

  /**
   * Get the {@link dagger.ObjectGraph} for this application.
   *
   * @return {@link #applicationGraph}
   */
  ObjectGraph getApplicationGraph() {
    return applicationGraph;
  }
}
