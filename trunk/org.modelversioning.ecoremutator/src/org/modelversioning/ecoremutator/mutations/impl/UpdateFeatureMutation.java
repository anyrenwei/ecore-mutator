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

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.modelversioning.ecoremutator.IModelProvider;
import org.modelversioning.ecoremutator.mutations.AbstractMutation;
import org.modelversioning.ecoremutator.tracker.IMutationTracker;

/**
 * Updates a random {@link EStructuralFeature} of a random {@link EObject}.
 * 
 * @author <a href="mailto:langer@big.tuwien.ac.at">Philip Langer</a>
 * 
 */
public class UpdateFeatureMutation extends AbstractMutation {

	/**
	 * {@inheritDoc}
	 * 
	 * Updates a random {@link EStructuralFeature} of a random {@link EObject}.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean mutate(IModelProvider modelProvider, IMutationTracker tracker) {
		boolean success = false;
		Exception occurredException = null;

		// get object to update
		EObject eObjectToUpdate = modelProvider.getRandomEObject();
		if (eObjectToUpdate != null) {
			// get target feature
			EStructuralFeature targetFeature = modelProvider
					.getRandomNonContainmentFeature(eObjectToUpdate);
			if (targetFeature != null) {
				// get random value
				Object value = modelProvider.getRandomValue(targetFeature);
				if (value != null) {
					try {
						if (!targetFeature.isMany()) {
							eObjectToUpdate.eSet(targetFeature, value);
							success = true;
						} else {
							// get list
							Object listValue = eObjectToUpdate.eGet(
									targetFeature, true);
							success = true;
							if (listValue instanceof List) {
								((List) listValue).add(value);
								success = true;
							}
						}
						if (success) {
							String message = "Updated "
									+ eObjectToUpdate.toString() + " at "
									+ targetFeature.getName() + " to " + value;
							log(IStatus.INFO, message);
							// track mutation
							tracker.track(this.getId(), message, true,
									toEObjectList(eObjectToUpdate),
									toFeatureList(targetFeature));
							return true;
						}
					} catch (ClassCastException cce) {
						occurredException = cce;
					} catch (IllegalArgumentException iae) {
						occurredException = iae;
					} catch (ArrayStoreException ase) {
						occurredException = ase;
					}
				}
			}
		}
		// track failing mutation
		String message = "Model provider did not return a suitable object, "
				+ "target feature, or value to update.";
		log(IStatus.WARNING, message, occurredException);
		tracker.track(this.getId(), message, false, toEObjectList(null),
				toFeatureList(null));

		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getId() {
		return "mutation.updateFeature";
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canHandleEditingDomain() {
		return false;
	}
}
