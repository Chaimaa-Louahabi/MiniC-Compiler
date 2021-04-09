/**
 * 
 */
package fr.n7.stl.block.ast.type.declaration;

import fr.n7.stl.block.ast.scope.Declaration;
import fr.n7.stl.block.ast.type.AtomicType;
import fr.n7.stl.block.ast.type.Type;

/**
 * Implementation of the Abstract Syntax Tree node for a field in a record.
 * @author Marc Pantel
 *
 */
public class LabelDeclaration implements Declaration {

	private String name;
	private Type type;
	public LabelDeclaration(String _name) {
		this.name = _name;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Declaration#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public Type getType() {
		return this.type; // TODO : Should be the type of the enum containing the label...
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.name + ";";
	}
	
	public void setType(Type labelType) {
		this.type = labelType;
	}

}
