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
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.modelversioning.ecoremutator.IModelProvider;
import org.modelversioning.ecoremutator.mutations.AbstractMutation;
import org.modelversioning.ecoremutator.tracker.IMutationTracker;

/**
 * Adds a random {@link EReference}.
 * 
 * @author <a href="mailto:langer@big.tuwien.ac.at">Philip Langer</a>
 * 
 */
public class AddEReferenceMutation extends AbstractMutation {

	/**
	 * {@inheritDoc}
	 * 
	 * Adds a random {@link EClass}.
	 */
	@Override
	public boolean mutate(IModelProvider modelProvider, IMutationTracker tracker) {
		boolean success = false;
		Exception occurredException = null;

		EObject eClassObject = modelProvider
				.getRandomEObject(EcorePackage.eINSTANCE.getEClass());
		EObject eClassObject2 = modelProvider
				.getRandomEObject(EcorePackage.eINSTANCE.getEClass());
		if (eClassObject != null && eClassObject instanceof EClass
				&& eClassObject2 != null && eClassObject2 instanceof EClass) {
			EClass eClass = (EClass) eClassObject;
			EReference eReference = EcoreFactory.eINSTANCE.createEReference();
			eReference.setName(modelProvider.getRandomString());
			eReference.setEType((EClass) eClassObject2);
			eClass.getEStructuralFeatures().add(eReference);
			success = true;
			String message = "Created new EReference " + eReference.getName()
					+ " and added it to " + eClass.getName();
			log(IStatus.INFO, message);
			tracker.track(this.getId(), message, false, toEObjectList(eClass),
					toFeatureList(null));
		} else {
			success = false;
			String message = "Could not create reference.";
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
		return "mutation.ecore.addEReference";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canHandleEditingDomain() {
		return false;
	}
}
