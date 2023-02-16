package NonPageObjects;

import java.util.List;

public class Validation {

    public static String validateNegativeRateAmounts(List<RatesCompare> compares, Double threshold) {
        String msg = Constants.OK;
        for(RatesCompare comparison : compares) {
            if(comparison.getPayseraAmount().length() > 0) {
                double payseraAmount = Double.parseDouble(comparison.getPayseraAmount().replace(",", ""));
                for(String bankAmnt : comparison.getBankAmount()) {
                    if(bankAmnt.length() > 0) {
                        double bankAmount = Double.parseDouble(bankAmnt.replace(",",""));
                        double bankNegDiff = 0;
                        int index = comparison.getBankAmount().indexOf(bankAmnt);
                        if(comparison.getNegativeDiff().get(index).length() > 0) {
                            String diff = comparison.getNegativeDiff().get(index);
                            diff = diff.replace("(", "").replace(")", "")
                                    .trim().replace(",", "");
                            bankNegDiff = Double.parseDouble(diff);
                        }

                        msg = validateRateAmountDiff(payseraAmount, bankAmount, bankNegDiff, threshold);
                        if(!msg.equals(Constants.OK))
                            return "Wrong or missing negative diff for currency " + comparison.getCurrency() + " - " + msg;
                    }
                }
            }
        }

        return msg;
    }

    private static String validateRateAmountDiff(Double payseraAmount, Double bankAmount, Double bankNegDiff, Double threshold) {
        Double negativeResult = (payseraAmount > bankAmount) ? bankAmount - payseraAmount : 0;
        if(Math.abs(negativeResult - bankNegDiff) > threshold)
            return "PayseraNumber: " + payseraAmount + " bankAmount: " + bankAmount + " bankNegDiff: " + bankNegDiff;
        else
            return Constants.OK;
    }
}
