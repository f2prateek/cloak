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

import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.InjectView;
import com.f2prateek.cloak.CloakedActivity;
import com.squareup.otto.Bus;
import javax.inject.Inject;

/**
 * {@link com.f2prateek.cloak.sample.SampleActivity#packageInfo} is provided by {@link
 * com.f2prateek.cloak.AndroidModule} from the library.
 *
 * {@link com.f2prateek.cloak.sample.SampleActivity#bus} is provided by {@link
 * com.f2prateek.cloak.sample.SampleApplication.SampleModule} from our own app.
 */
public class SampleActivity extends CloakedActivity {

  @Inject PackageInfo packageInfo;
  @Inject Bus bus;

  @InjectView(R.id.textView_info) TextView textView_info;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    textView_info.setText(packageInfo.packageName);
  }
}
