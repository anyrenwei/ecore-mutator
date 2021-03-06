/**
 * <copyright>
 *
 * Copyright (c) 2010 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * </copyright>
 */

package org.modelversioning.ecoremutator.mutations.impl;

import org.modelversioning.ecoremutator.IModelProvider;
import org.modelversioning.ecoremutator.mutations.ModelProvider;

/**
 * @author <a href="mailto:langer@big.tuwien.ac.at">Philip Langer</a>
 * 
 */
public class AddAnnotationMutationTest extends MutationTestCase {

	/**
	 * Test method for
	 * {@link org.modelversioning.ecoremutator.mutations.impl.AddAnnotationMutationMutation#mutate(org.modelversioning.ecoremutator.mutations.ModelProvider, org.modelversioning.ecoremutator.tracker.IMutationTracker)}
	 * .
	 */
	public void testMutate() {
		// initialize model provider
		IModelProvider modelProvider = new ModelProvider();
		modelProvider.setModelResource(sample1Resource);

		AddAnnotationMutation mutation = new AddAnnotationMutation();
		for (int i = 0; i < 10; i++) {
			mutation.mutate(modelProvider, this);
		}
	}

}
