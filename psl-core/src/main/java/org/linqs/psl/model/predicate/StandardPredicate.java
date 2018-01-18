/*
 * This file is part of the PSL software.
 * Copyright 2011-2015 University of Maryland
 * Copyright 2013-2018 The Regents of the University of California
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
package org.linqs.psl.model.predicate;

import org.linqs.psl.model.term.ConstantType;

/**
 * Predicate of GroundAtoms that can be persisted.
 * Standard predicates cannot have arguments of DeferredFunctionalUniqueID
 * since the underlying storage type is not known.
 */
public class StandardPredicate extends Predicate {
	private boolean isBlock;

	/**
	 * Sole constructor.
	 *
	 * @param name  name for this predicate
	 * @param types  types for each of the predicate's arguments
	 * @see PredicateFactory
	 */
	public StandardPredicate(String name, ConstantType[] types) {
		super(name, types);
		isBlock = false;

		for (ConstantType type : types) {
			if (type == ConstantType.DeferredFunctionalUniqueID) {
				throw new IllegalArgumentException(
						"DeferredFunctionalUniqueID can only be used with FunctionalPredicates" +
						" (and should only be used in rare cases.");
			}
		}
	}

	public void setBlock(boolean isBlock) {
		this.isBlock = isBlock;
	}

	public boolean isBlock() {
		return isBlock;
	}
}