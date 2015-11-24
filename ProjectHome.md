# What is the Ecore Mutator? #

Ecore Mutator is an EMF-based framework to randomly mutate EMF models conforming to a Ecore metamodel. Out of the box, this framework provides several mutations such as mutations which add new instances of randomly selected meta classes in the metamodel, remove existing model elements from the model, change attribute values in existing model elements, etc. However, the Ecore Mutator may easily be extended by custom mutations.

# Why do I need the Ecore Mutator? #

Ecore Mutator can be used to test, benchmark, and evaluate tools and frameworks related to model matching, differencing, tracking, etc.

# How can I use the Ecore Mutator? #

So far, the Ecore Mutator is _only_ a framework. Thus, you can only use it by programmatically calling its API. [Check-out](http://eclipselabs.org/p/ecore-mutator/source/checkout) the code, import the projects to Eclipse and start using it (cf. [Usage](Usage.md)). However, a user interface for applying mutations to models is planned in future.

# How can I contribute? #

Everyone is kindly invited to contribute to this project! We are also happy to answer your questions, consider your feedback, and try to fix your [issues](http://eclipselabs.org/p/ecore-mutator/issues/list). If you are interested in contributing code, please [check out](http://eclipselabs.org/p/ecore-mutator/source/checkout) the code and contact us!