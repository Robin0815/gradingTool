package Control.Strategy.UseCaseStrategy.Visitor;

import Model.*;
import Model.Class;
import Model.UMLSystem;

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
    void visit(UMLSystem system);
    void visit(UnknownElement unknownElement);
    void visit(UnknownRelation unknownRelation);
    void visit(UseCase useCase);
    void visit(NonHumanActor nonHumanActor);
    void visit(Constructor constructor);
    void visit(Association2 association2);
}
