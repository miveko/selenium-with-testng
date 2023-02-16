package NonPageObjects;

import java.util.ArrayList;
import java.util.List;

public class RatesCompare {
    private String currency;
    private String payseraAmount;
    private final List<String> bankAmount;
    private final List<String> negativeDiff;
    public RatesCompare() {
        bankAmount = new ArrayList<>();
        negativeDiff = new ArrayList<>();
    }

    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPayseraAmount() {
        return payseraAmount;
    }

    public void setPayseraAmount(String payseraAmount) {
        this.payseraAmount = payseraAmount;
    }

    public List<String> getBankAmount() {
        return bankAmount;
    }

    public List<String> getNegativeDiff() {
        return negativeDiff;
    }
}
