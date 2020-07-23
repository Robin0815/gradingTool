/*
@author mkowol2s
 */

package Model;

import Control.Strategy.UseCaseStrategy.Visitor.Visitor;

public interface UMLComponent {
    public final String id = null;
    boolean isConnectable();
    void accept(Visitor visitor);
}
