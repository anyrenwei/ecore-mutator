To randomly mutate a specified model for, let's say, 50 times with all provided mutations, use the code below. Please note that the mutations can be applied to any ecore-based model (a model conforming to a metamodel specified in Ecore). Currently, it is not checked whether the resulting model is valid (conforming to constraints of the metamodel). This might be enhanced using the EMF Validation framework in future releases.

Instead of the provided mutations you also can specify your own implementations of the `Mutation` interface or by specializing the `AbstractMutation` class.

```
// initialize mutator
EcoreMutator mutator = new EcoreMutator();

// configure mutations to apply
mutator.addMutation(new AddAnnotationMutation());
mutator.addMutation(new AddObjectMutation());
mutator.addMutation(new DeleteObjectMutation());
mutator.addMutation(new MoveObjectMutation());
mutator.addMutation(new UnsetFeatureMutation());
mutator.addMutation(new UpdateFeatureMutation());

// configure a change tracker
mutator.setTracker();

// load input model to mutate (any ecore-based model resource)
Resource inputResource = loadResource(inputUri);

// initialize model provider
IModelProvider modelProvider = new ModelProvider();
modelProvider.setModelResource(inputResource);

// mutate model
mutator.mutate(modelProvider, 50);

// save output model
Resource outputResource = createResource(outputUri);
outputResource.getContents().addAll(inputResource.getContents());
outputResource.save(Collections.emptyMap());
```