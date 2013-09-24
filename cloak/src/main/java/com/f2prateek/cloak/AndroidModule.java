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

import android.accounts.AccountManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Module for all Android related provisions.
 */
@Module(
    complete = false,
    library = true)
public class AndroidModule {

  private final CloakedApplication application;

  public AndroidModule(CloakedApplication application) {
    this.application = application;
  }

  /**
   * Allow the application context to be injected but require that it be annotated with
   * {@link ForApplication @ForApplication} to explicitly differentiate it from an activity
   * context.
   */
  @Provides @Singleton @ForApplication Context provideApplicationContext() {
    return application;
  }

  @Provides @Singleton SharedPreferences provideDefaultSharedPreferences() {
    return PreferenceManager.getDefaultSharedPreferences(application);
  }

  @Provides @Singleton PackageInfo providePackageInfo() {
    try {
      return application.getPackageManager().getPackageInfo(application.getPackageName(), 0);
    } catch (PackageManager.NameNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  @SuppressWarnings("unchecked")
  public <T> T getSystemService(Context context, String serviceConstant) {
    return (T) context.getSystemService(serviceConstant);
  }

  @Provides ApplicationInfo provideApplicationInfo() {
    return application.getApplicationInfo();
  }

  @Provides AccountManager provideAccountManager() {
    return AccountManager.get(application);
  }

  @Provides ClassLoader provideClassLoader() {
    return application.getClassLoader();
  }
}
