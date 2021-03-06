/*
 * Copyright (c) 2008-2017 Haulmont.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.haulmont.cuba.web.tmp;

import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.components.data.value.ContainerValueSource;
import com.haulmont.cuba.gui.model.DataContextFactory;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.model.InstanceLoader;
import com.haulmont.cuba.security.entity.User;

import javax.inject.Inject;
import java.util.Map;
import java.util.UUID;

/**
 *
 */
public class DcScreen1 extends AbstractWindow {

    @Inject
    private TextField<String> textField1;
    @Inject
    private TextField<String> textField2;
    @Inject
    private TextField<String> textField3;
    @Inject
    private TextField<String> textField4;

    @Inject
    private DataContextFactory dataContextFactory;

    private InstanceContainer<User> container;

    @Override
    public void init(Map<String, Object> params) {
        container = dataContextFactory.createInstanceContainer(User.class);

        textField1.setValueSource(new ContainerValueSource<>(container, "name"));
        textField2.setValueSource(new ContainerValueSource<>(container, "name"));
        textField3.setValueSource(new ContainerValueSource<>(container, "group.name"));
        textField4.setValueSource(new ContainerValueSource<>(container, "group.name"));

        InstanceLoader<User> loader = dataContextFactory.createInstanceLoader();
        loader.setContainer(container);
        loader.setEntityId(UUID.fromString("60885987-1b61-4247-94c7-dff348347f93"));
        loader.setView("user.edit");
        loader.load();
    }

    public void changeName() {
        container.getItem().setName("User-" + System.currentTimeMillis());
    }
}
