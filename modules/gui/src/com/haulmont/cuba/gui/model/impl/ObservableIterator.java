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

package com.haulmont.cuba.gui.model.impl;

import com.google.common.collect.ForwardingIterator;

import java.util.Iterator;

/**
 *
 */
class ObservableIterator<T> extends ForwardingIterator<T> {

    private Iterator<T> delegate;
    private Runnable onCollectionChanged;

    protected ObservableIterator(Iterator<T> delegate, Runnable onCollectionChanged) {
        this.delegate = delegate;
        this.onCollectionChanged = onCollectionChanged;
    }

    protected void fireCollectionChanged() {
        if (onCollectionChanged != null)
            onCollectionChanged.run();
    }

    @Override
    protected Iterator<T> delegate() {
        return delegate;
    }

    @Override
    public void remove() {
        super.remove();
        fireCollectionChanged();
    }
}