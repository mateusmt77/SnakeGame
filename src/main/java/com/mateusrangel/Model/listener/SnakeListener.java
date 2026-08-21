package com.mateusrangel.Model.listener;

import com.mateusrangel.Model.entity.Snake;
import com.mateusrangel.Model.enums.DeadReason;

public interface SnakeListener {
    void onGrow(Snake snake, int newSnakeLength);
    void onDie(Snake snake, DeadReason deadReason);
}
