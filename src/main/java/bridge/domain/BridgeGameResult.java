package bridge.domain;

import java.util.Objects;

public class BridgeGameResult {
    private final StringBuilder upperBridge;
    private final StringBuilder lowerBridge;

    public BridgeGameResult() {
        upperBridge = new StringBuilder();
        lowerBridge = new StringBuilder();

        upperBridge.append(BridgeComponent.BRIDGE_START.get());
        lowerBridge.append(BridgeComponent.BRIDGE_START.get());
    }

    public String getUpperBridge() {
        return upperBridge.toString();
    }

    public String getLowerBridge() {
        return lowerBridge.toString();
    }

    public void makeMoveResult(Bridge bridge, int currentPosition, String direction) {
        replaceBridgeEndToSeparator(bridge, currentPosition);
        tryMoveBridge(bridge, currentPosition, direction);
        addBridgeEnd();
    }

    private void replaceBridgeEndToSeparator(Bridge bridge, int currentPosition) {
        if (currentPosition > 0 && currentPosition < bridge.getSize()) {
            deleteBridgeEnd();
            addSeparator();
        }
    }

    private void deleteBridgeEnd() {
        upperBridge.deleteCharAt(upperBridge.length() - 1);
        lowerBridge.deleteCharAt(lowerBridge.length() - 1);
    }

    private void addSeparator() {
        upperBridge.append(BridgeComponent.BRIDGE_SEPARATOR.get());
        lowerBridge.append(BridgeComponent.BRIDGE_SEPARATOR.get());
    }

    private void tryMoveBridge(Bridge bridge, int currentPosition, String direction) {
        if (Objects.equals(direction, Direction.U.name())) {
            tryMoveUpperBridge(bridge, currentPosition, direction);
        }
        if (Objects.equals(direction, Direction.D.name())) {
            tryMoveLowerBridge(bridge, currentPosition, direction);
        }
    }

    private void tryMoveUpperBridge(Bridge bridge, int currentPosition, String direction) {
        if (bridge.isPossibleMove(currentPosition, direction)) {
            moveUpperBridge();
        }
        if (!bridge.isPossibleMove(currentPosition, direction)) {
            moveFailUpperBridge();
        }
    }

    private void moveUpperBridge() {
        upperBridge.append(BridgeComponent.MOVE_SUCCESS.get());
        lowerBridge.append(BridgeComponent.BLANK.get());
    }

    private void moveFailUpperBridge() {
        upperBridge.append(BridgeComponent.MOVE_FAILURE.get());
        lowerBridge.append(BridgeComponent.BLANK.get());
    }

    private void tryMoveLowerBridge(Bridge bridge, int currentPosition, String direction) {
        if (bridge.isPossibleMove(currentPosition, direction)) {
            moveLowerBridge();
        }
        if (!bridge.isPossibleMove(currentPosition, direction)) {
            moveFailLowerBridge();
        }
    }

    private void moveLowerBridge() {
        lowerBridge.append(BridgeComponent.MOVE_SUCCESS.get());
        upperBridge.append(BridgeComponent.BLANK.get());
    }

    private void moveFailLowerBridge() {
        lowerBridge.append(BridgeComponent.MOVE_FAILURE.get());
        upperBridge.append(BridgeComponent.BLANK.get());
    }

    private void addBridgeEnd() {
        upperBridge.append(BridgeComponent.BRIDGE_END.get());
        lowerBridge.append(BridgeComponent.BRIDGE_END.get());
    }
}
