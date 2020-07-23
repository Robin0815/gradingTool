package Control.Visitor;

import Model.*;
import Model.Class;
import Model.System;

import java.util.HashMap;
import java.util.Map;

public class SyntaxVisitor implements Visitor{
    private Map<ErrorTypes, ErrorWrapper> numberOfErrors = new HashMap<>();

    public Map<ErrorTypes, ErrorWrapper> getNumberOfErrors() {
        return numberOfErrors;
    }

    public void setNumberOfErrors(Map<ErrorTypes, ErrorWrapper> numberOfErrors) {
        this.numberOfErrors = numberOfErrors;
    }

    @Override
    public void visit(Actor actor) {

    }

    @Override
    public void visit(Aggregation aggregation) {

    }

    @Override
    public void visit(Association association) {

    }

    @Override
    public void visit(Attribut attribut) {

    }

    @Override
    public void visit(Class umlclass) {

    }

    @Override
    public void visit(Composition composition) {

    }

    @Override
    public void visit(ConditionRelation conditionRelation) {

    }

    @Override
    public void visit(Dependency dependency) {

    }

    @Override
    public void visit(Diagram diagram) {

    }

    @Override
    public void visit(Extends extRel) {

    }

    @Override
    public void visit(ExtensionPoint extensionPoint) {

    }

    @Override
    public void visit(Generalization generalization) {

    }

    @Override
    public void visit(Implements implRel) {

    }

    @Override
    public void visit(Includes includes) {

    }

    @Override
    public void visit(Inheritance inheritance) {

    }

    @Override
    public void visit(Method method) {

    }

    @Override
    public void visit(Note note) {

    }

    @Override
    public void visit(System system) {

    }

    @Override
    public void visit(UnknownElement unknownElement) {

    }

    @Override
    public void visit(UnknownRelation unknownRelation) {

    }

    @Override
    public void visit(Use use) {

    }

    @Override
    public void visit(UseCase useCase) {

    }
}
