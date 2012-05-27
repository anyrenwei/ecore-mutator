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
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.modelversioning.ecoremutator.IModelProvider;
import org.modelversioning.ecoremutator.mutations.AbstractMutation;
import org.modelversioning.ecoremutator.tracker.IMutationTracker;

/**
 * Adds a random {@link EAttribute}.
 * 
 * @author <a href="mailto:langer@big.tuwien.ac.at">Philip Langer</a>
 * 
 */
public class AddEAttributeMutation extends AbstractMutation {

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
		if (eClassObject != null && eClassObject instanceof EClass) {
			EClass eClass = (EClass) eClassObject;
			EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
			eAttribute.setName(modelProvider.getRandomString());
			eAttribute.setEType(EcorePackage.eINSTANCE.getEInt());
			eClass.getEStructuralFeatures().add(eAttribute);
			success = true;
			String message = "Created new EAttribute " + eAttribute.getName()
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
		return "mutation.ecore.addEAttribute";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canHandleEditingDomain() {
		return false;
	}
}
