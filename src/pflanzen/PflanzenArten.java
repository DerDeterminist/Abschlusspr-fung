package pflanzen;

public enum PflanzenArten {

    Mais(2,100),
    Weizen(4,100);

    private double wachstum;
    private double erntehoehe;

    PflanzenArten(double wachstum, double erntehoehe)
    {
        this.wachstum = wachstum;
        this.erntehoehe = erntehoehe;
    }

    public double getWachstum() {
        return wachstum;
    }
    public double getErntehöhe(){return erntehoehe;}
}
