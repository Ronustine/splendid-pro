package com.ronustine.splendidpro.validation.entity;


import com.ronustine.splendidpro.validation.entity.dto.ValidateFieldInfoDTO;

import java.util.Iterator;
import java.util.Stack;

/**
 * @Description 字段的迭代器
 * @Author ronustine
 * @Date 2018/9/20/0020
 */
public class ValidateFieldInfoIterator implements Iterator<ValidateFieldInfoDTO> {

	// LinkedList
	private Stack<Iterator> stack = new Stack<>();

	public ValidateFieldInfoIterator(Iterator iterator) {
		stack.push(iterator);
	}

	@Override
	public boolean hasNext() {
		if (stack.empty()) {
			return false;
		}

		Iterator iterator = stack.peek();
		if (!iterator.hasNext()) {
			stack.pop();
			return hasNext();
		}
		return true;
	}

	@Override
	public ValidateFieldInfoDTO next() {
		if (hasNext()) {
			Iterator iterator = stack.peek();
			ValidateFieldInfoDTO component = (ValidateFieldInfoDTO) iterator.next();
			if (component instanceof ValidateFieldInfoDTO) {
				Iterator subIterator = component.createIterator();
				if (null != subIterator && !stack.contains(subIterator)) {
					stack.push(subIterator);
				}
			}
			return component;
		}
		return null;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
