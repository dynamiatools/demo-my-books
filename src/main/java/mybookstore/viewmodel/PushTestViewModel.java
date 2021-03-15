/*
 * Copyright (C) 2021 Dynamia Soluciones IT S.A.S - NIT 900302344-1
 * Colombia / South America
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mybookstore.viewmodel;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import tools.dynamia.integration.scheduling.SchedulerUtil;
import tools.dynamia.zk.websocket.WebSocketPushSender;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

public class PushTestViewModel {

    private int steps = 5;
    private List<String> messages = new ArrayList<>();
    private int progress;

    @Command
    public void startTest() {
        Desktop desktop = Executions.getCurrent().getDesktop();
        progress = 0;
        SchedulerUtil.run(() -> IntStream.range(1, steps).forEach(s -> {
            WebSocketPushSender.sendPushCommand(desktop, "pushTest");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
    }

    @Command
    public void broadcast() {
        progress = 0;
        SchedulerUtil.run(() -> IntStream.range(1, steps).forEach(s -> {
            WebSocketPushSender.broadcastCommand("pushTest");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
    }


    @Command
    @NotifyChange("*")
    public void clear() {
        messages.clear();
        progress = 0;
    }


    @GlobalCommand
    @NotifyChange("*")
    public void pushTest() {
        messages.add("Notification send from the server - " + new Date());
        progress = progress + 100 / steps;
    }

    public List<String> getMessages() {
        return messages;
    }

    public int getProgress() {
        return progress;
    }
}
