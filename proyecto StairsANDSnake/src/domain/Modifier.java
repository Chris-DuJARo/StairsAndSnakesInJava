package domain;

public abstract class Modifier implements Item {

	private final Valor cara;
	public Modifier(Valor cara) {
		this.cara = cara;
		SetObject();
	}

	@Override
	public abstract void DoAction(Ficha ficha);

	@Override
	public abstract String toString();

	@Override
	public void SetObject() {
		cara.setModifier(this);
	}
}
