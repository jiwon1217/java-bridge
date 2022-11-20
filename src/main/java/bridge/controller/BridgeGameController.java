package bridge.controller;

import bridge.BridgeMaker;
import bridge.BridgeRandomNumberGenerator;
import bridge.domain.Bridge;
import bridge.domain.BridgeGame;
import bridge.view.InputView;
import bridge.view.OutputView;
import java.util.Objects;

public class BridgeGameController {
    private final BridgeMaker bridgeMaker;
    private BridgeGame bridgeGame;
    private Bridge bridge;
    boolean isGameOver;

    public BridgeGameController() {
        this.bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
        isGameOver = false;
    }

    public void doBridgeGame() {
        OutputView.printGameStartMessage();
        initBridgeGame();
        do {
            boolean isMoveFail = moveForward(bridgeGame);
            if (isMoveFail) {
                isGameOver = handleRestartAndQuit(InputView.readGameCommand(), bridgeGame, bridge);
            }
            isGameOver = isEndOfBridge(bridge, bridgeGame, isGameOver);
        } while (!isGameOver);
    }

    private void initBridgeGame() {
        bridge = new Bridge(bridgeMaker.makeBridge(InputView.readBridgeSize()));
        bridgeGame = new BridgeGame(bridge);
    }

    private boolean moveForward(BridgeGame bridgeGame) {
        boolean isMoveFail = bridgeGame.move(InputView.readMoving());
        OutputView.printMap(bridgeGame.getBridgeGameResult());
        return isMoveFail;
    }

    private boolean handleRestartAndQuit(String command, BridgeGame bridgeGame, Bridge bridge) {
        if (Objects.equals(command, "R")) {
            bridgeGame.retry(bridge);
            return false;
        }
        OutputView.printResult(bridgeGame);
        return true;
    }

    private boolean isEndOfBridge(Bridge bridge, BridgeGame bridgeGame, boolean isGameOver) {
        if (bridgeGame.getCurrentPosition() == bridge.getSize() - 1) {
            isGameOver = true;
            OutputView.printResult(bridgeGame);
        }
        return isGameOver;
    }
}
