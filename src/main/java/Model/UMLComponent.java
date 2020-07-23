/*
@author mkowol2s
 */

package Model;

import Control.Strategy.UseCaseStrategy.Visitor.Visitor;

public interface UMLComponent {
    public Elements id();
    boolean isConnectable();
    void accept(Visitor visitor);
}
