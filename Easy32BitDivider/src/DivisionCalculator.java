public class DivisionCalculator {

    public void calculate(long dividend, long divisor, DivisionCallback callback) {
        long quotient = 0;
        long remainder = 0;

        for (int i = 31; i >= 0; i--) {
            remainder = (remainder << 1) | ((dividend >> i) & 1);
            if (remainder >= divisor) {
                remainder -= divisor;
                quotient = (quotient << 1) | 1;
            } else {
                quotient = quotient << 1;
            }

            callback.onStep(quotient, remainder);

            try {
                Thread.sleep(0); // 添加延迟以便观察
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
