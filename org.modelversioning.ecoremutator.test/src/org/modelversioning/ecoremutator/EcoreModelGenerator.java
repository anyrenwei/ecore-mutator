/**
 * <copyright>
 *
 * Copyright (c) 2011 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * </copyright>
 */

package org.modelversioning.ecoremutator;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.modelversioning.ecoremutator.mutations.ModelProvider;
import org.modelversioning.ecoremutator.mutations.ecore.impl.AddSuperTypeMutation;

public class EcoreModelGenerator extends TestCase {

	private static final int NUMBER_OF_OBJECTS = 1875;
	ResourceSet resourceSet = new ResourceSetImpl();
	Resource sample1Resource;
	private IModelProvider modelProviderSample1;

	/**
	 * @throws java.lang.Exception
	 */
	protected void setUp() throws Exception {
		// load sample 1 model
		sample1Resource = loadResource("../org.eclipselabs.modelversioningbenchmarks/models/performance/xxxlarge/initial_left.ecore");
		Assert.assertTrue(sample1Resource.getContents().size() == 1);
		// initialize sample 1 model provider
		modelProviderSample1 = new ModelProvider();
		modelProviderSample1.setModelResource(sample1Resource);
	}

	/**
	 * Loads the resource with the specified <code>fileURI</code>.
	 * 
	 * @param fileURI
	 *            to load.
	 * @return loaded resource.
	 */
	private Resource loadResource(String fileURIString) {
		URI fileURI = URI.createFileURI(new File(fileURIString)
				.getAbsolutePath());
		resourceSet
				.getResourceFactoryRegistry()
				.getExtensionToFactoryMap()
				.put("ecore",
						new org.modelversioning.core.impl.UUIDResourceFactoryImpl());
		return resourceSet.getResource(fileURI, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		sample1Resource.unload();
	}

	public void testGenerateModel() throws IOException {
		EcoreMutator ecoreMutator = new EcoreMutator();
		// ecoreMutator.addMutation(new AddEClassMutation(), 1);
		// ecoreMutator.addMutation(new AddEReferenceMutation(), 1);
		// ecoreMutator.addMutation(new AddEAttributeMutation(), 1);
		ecoreMutator.addMutation(new AddSuperTypeMutation(), 1);
		// ecoreMutator.addMutation(new AddObjectMutation(), 1);
		ecoreMutator.mutate(modelProviderSample1, NUMBER_OF_OBJECTS);
		sample1Resource.save(null);
	}

}
