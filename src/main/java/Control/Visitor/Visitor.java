package Control.Visitor;

import Model.*;
import Model.Class;
import Model.System;

public interface Visitor {
    void visit(Actor actor);
    void visit(Aggregation aggregation);
    void visit(Association association);
    void visit(Attribut attribut);
    void visit(Class umlclass);
    void visit(Composition composition);
    void visit(ConditionRelation conditionRelation);
    void visit(Dependency dependency);
    void visit(Diagram diagram);
    void visit(Extends extRel);
    void visit(ExtensionPoint extensionPoint);
    void visit(Generalization generalization);
    void visit(Implements implRel);
    void visit(Includes includes);
    void visit(Inheritance inheritance);
    void visit(Method method);
    void visit(Note note);
    void visit(System system);
    void visit(UnknownElement unknownElement);
    void visit(UnknownRelation unknownRelation);
    void visit(Use use);
    void visit(UseCase useCase);
}
