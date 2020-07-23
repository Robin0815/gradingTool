/*
@author mkowol2s
 */

package Model;

import Control.Strategy.UseCaseStrategy.Visitor.Visitor;

public interface UMLComponent {
    public String id();
    boolean isConnectable();
    void accept(Visitor visitor);
}
