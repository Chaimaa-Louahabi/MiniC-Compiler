package fr.n7.stl.block.ast.expression;

import fr.n7.stl.block.ast.scope.Declaration;
import fr.n7.stl.block.ast.scope.HierarchicalScope;
import fr.n7.stl.block.ast.type.RecordType;
import fr.n7.stl.block.ast.type.*;
import fr.n7.stl.block.ast.type.declaration.FieldDeclaration;
import fr.n7.stl.util.Logger;

/**
 * Common elements between left (Assignable) and right (Expression) end sides of assignments. These elements
 * share attributes, toString and getType methods.
 * @author Marc Pantel
 *
 */
public abstract class AbstractField implements Expression {

	protected Expression record;
	protected String name;
	protected FieldDeclaration field;
	
	/**
	 * Construction for the implementation of a record field access expression Abstract Syntax Tree node.
	 * @param _record Abstract Syntax Tree for the record part in a record field access expression.
	 * @param _name Name of the field in the record field access expression.
	 */
	public AbstractField(Expression _record, String _name) {
		this.record = _record;
		this.name = _name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.record + "." + this.name;
	}
	
	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.expression.Expression#collect(fr.n7.stl.block.ast.scope.HierarchicalScope)
	 */
	@Override
	public boolean collect(HierarchicalScope<Declaration> _scope) {
		return this.record .collect(_scope);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.expression.Expression#resolve(fr.n7.stl.block.ast.scope.HierarchicalScope)
	 */
	@Override
	public boolean resolve(HierarchicalScope<Declaration> _scope) {
		if(this.record.getType() instanceof NamedType) {
			Type temp = ((NamedType)this.record.getType()).getType();
			if(temp instanceof RecordType) {
				if (((RecordType)temp).contains(this.name)) {
					this.field = ((RecordType)temp).get(this.name);
				} else {
					Logger.error(this.record +" has no field named " + this.name);
				}
				
			} else {
				Logger.error(this.record +" is not a record.");
			}
		}
		return record.resolve(_scope);
	}

	/**
	 * Synthesized Semantics attribute to compute the type of an expression.
	 * @return Synthesized Type of the expression.
	 */
	public Type getType() {
		return this.field.getType();
	}

}