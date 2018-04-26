package pflanzen;

public abstract class FeldPflanzen {

    String name;
    double hoehe;
    PflanzenArten pflanzenArten;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public double getHoehe() { return hoehe; }

    public void setHoehe(double hoehe) { this.hoehe = hoehe; }

    public PflanzenArten getPflanzenArten() { return pflanzenArten; }

    public void setPflanzenArten(PflanzenArten pflanzenArten) { this.pflanzenArten = pflanzenArten; }


}
