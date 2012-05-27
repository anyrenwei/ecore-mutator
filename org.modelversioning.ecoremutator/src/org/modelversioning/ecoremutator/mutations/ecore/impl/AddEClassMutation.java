/**
 * <copyright>
 *
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * </copyright>
 */

package org.modelversioning.ecoremutator.mutations.ecore.impl;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.modelversioning.ecoremutator.IModelProvider;
import org.modelversioning.ecoremutator.mutations.AbstractMutation;
import org.modelversioning.ecoremutator.tracker.IMutationTracker;

/**
 * Adds a random {@link EClass}.
 * 
 * @author <a href="mailto:langer@big.tuwien.ac.at">Philip Langer</a>
 * 
 */
public class AddEClassMutation extends AbstractMutation {

	/**
	 * {@inheritDoc}
	 * 
	 * Adds a random {@link EClass}.
	 */
	@Override
	public boolean mutate(IModelProvider modelProvider, IMutationTracker tracker) {
		boolean success = false;
		Exception occurredException = null;

		EObject ePackageObject = modelProvider
				.getRandomEObject(EcorePackage.eINSTANCE.getEPackage());
		if (ePackageObject != null && ePackageObject instanceof EPackage) {
			EPackage ePackage = (EPackage) ePackageObject;
			EClass eClass = EcoreFactory.eINSTANCE.createEClass();
			eClass.setName(modelProvider.getRandomString());
			ePackage.getEClassifiers().add(eClass);
			success = true;
			String message = "Created new EClass " + eClass.getName()
					+ " and added it to " + ePackage.getName();
			log(IStatus.INFO, message);
			tracker.track(this.getId(), message, false,
					toEObjectList(ePackage), toFeatureList(null));
		} else {
			success = false;
			String message = "Could not create class.";
			log(IStatus.INFO, message);
			log(IStatus.WARNING, message, occurredException);
			tracker.track(this.getId(), message, false, toEObjectList(null),
					toFeatureList(null));
		}

		return success;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getId() {
		return "mutation.ecore.addEClass";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canHandleEditingDomain() {
		return false;
	}
}
