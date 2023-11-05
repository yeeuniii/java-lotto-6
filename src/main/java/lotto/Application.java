package lotto;

public class Application {
    public static void main(String[] args) {
        Screen.printAskingPurchasingAmountMessage();
        Lottos lottos = buyLottos();
        Screen.printAllLottos(lottos.getLottos());
        Screen.printAskingWinningNumbersMessage();
        Lotto winningNumber = selectLottoWinningNumber();
        Screen.printAskingBonusNumberMessage();
        int bonusNumber = selectBonusNumber(winningNumber);
    }

    private static Lottos buyLottos() {
        try {
            return new Lottos(Screen.inputPurchasingAmount());
        }
        catch (IllegalArgumentException exception) {
            Screen.printErrorMessage(exception.getMessage());
        }
        return buyLottos();
    }

    private static Lotto selectLottoWinningNumber() {
        try {
            return new Lotto(Screen.inputWinningNumbers());
        }
        catch (IllegalArgumentException exception) {
            Screen.printErrorMessage(exception.getMessage());
        }
        return selectLottoWinningNumber();
    }

    private static int selectBonusNumber(Lotto winningNumber) {
        try {
            LottoNumber bonusNumber = new LottoNumber(Screen.inputBonusNumber());
            if (winningNumber.hasIt(bonusNumber))
                throw new IllegalArgumentException("[ERROR] 보너스 번호는 로또 번호와 중복되어서는 안됩니다.");
            return bonusNumber.getNumber();
        }
        catch (IllegalArgumentException exception) {
            Screen.printErrorMessage(exception.getMessage());
        }
        return selectBonusNumber(winningNumber);
    }
}
