public class CWalker extends  CBaseListener {
    public boolean InsideScope;

    public boolean getScopeState(){
        return InsideScope;
    }

    @Override public void enterCompoundStatement(CParser.CompoundStatementContext ctx) {
        InsideScope = true ;
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */

    @Override public void exitCompoundStatement(CParser.CompoundStatementContext ctx) {
        InsideScope = false ;
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
}
