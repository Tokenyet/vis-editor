/*
 * Copyright 2014-2016 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kotcrab.vis.ui.util.adapter;

import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Built-in adapter implementation for {@link ArrayList}.
 * @author Kotcrab
 * @since 1.0.0
 */
public abstract class ArrayListAdapter<ItemT, ViewT extends Actor> extends AbstractListAdapter<ItemT, ViewT> {
	private ArrayList<ItemT> list;

	public ArrayListAdapter (ArrayList<ItemT> list) {
		this.list = list;
	}

	@Override
	public Iterable<ItemT> iterable () {
		return list;
	}

	public ItemT set (int index, ItemT element) {
		ItemT res = list.set(index, element);
		itemsChanged();
		return res;
	}

	public boolean add (ItemT element) {
		boolean res = list.add(element);
		itemAdded(element);
		return res;
	}

	public void add (int index, ItemT element) {
		list.add(index, element);
		itemAdded(element);
	}

	public ItemT remove (int index) {
		ItemT res = list.remove(index);
		if (res != null) itemRemoved(res);
		return res;
	}

	public boolean remove (ItemT item) {
		boolean res = list.remove(item);
		if (res) itemRemoved(item);
		return res;
	}

	public void clear () {
		list.clear();
		itemsChanged();
	}

	public boolean addAll (Collection<? extends ItemT> c) {
		boolean res = list.addAll(c);
		itemsChanged();
		return res;
	}

	public boolean addAll (int index, Collection<? extends ItemT> c) {
		boolean res = list.addAll(index, c);
		itemsChanged();
		return res;
	}

	public boolean removeAll (Collection<?> c) {
		boolean res = list.removeAll(c);
		itemsChanged();
		return res;
	}
}