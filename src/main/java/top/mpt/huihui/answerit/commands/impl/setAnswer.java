package top.mpt.huihui.answerit.commands.impl;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import top.mpt.huihui.answerit.commands.ICommand;
import org.bukkit.command.CommandSender;
import top.mpt.huihui.answerit.utils.ChatUtils;
import top.mpt.huihui.answerit.utils.ConfigUtils;
import top.mpt.huihui.answerit.utils.PlayerUtils;
import static top.mpt.huihui.answerit.Main.*;

import java.util.List;
import java.util.Objects;

public class setAnswer extends ICommand {
    public setAnswer(){
        super("setanswer", "", "no-usage");
    }

    public static String answer = "";
    public static String answerText = "";
    public static Player sender = null;
    public static Player target = null;
    public boolean onCommand(CommandSender sender, String[] args){
        /* args[0] = "NameFlying"
         * args[1] = "Answer"
         * args[2] = "select"
         * args[3] = "灰灰怎么样?"
         * args[4] = "fakeAnswer,Answer"
         */
        if (sender instanceof Player){
            setAnswer.sender = (Player)sender;
            target = Bukkit.getPlayer(args[0]);
            answer = args[1];
            answerText = args[4];
            // 发送消息
            sendQuestion(((Player) sender).getPlayer(), args[2], args[3]);
        } else {
            sender.sendMessage((String) ConfigUtils.getConfig(config, "sender_err"));
        }
        return true;
    }

    public String permission(){
        return "answer.question";
    }


    /* from commands.impl.q(with Player Execute Command) */
    /**
     * 给玩家发送问题
     * @param sender 发出提问者
     * @param type 提问类型
     * @param text 提问文本
     */
    public void sendQuestion(Player sender, String type, String text){
        /* 消息格式：
         * 您收到了来自[X_huihui]的提问
         * 提问类型：Select
         * 提问内容：灰灰怎么样？
         * 回答选项/请在公屏上打出答案：[很好] [非常好] [不好] [完全不好]
         * 答对了有奖励，答错了有惩罚(?
         */
        List<String> global_receiver_info = (List<String>) ConfigUtils.getListConfig(config, "global.receiver_info");
        PlayerUtils.send(target, "#AQUA#=====================================");
        PlayerUtils.send(target, global_receiver_info.get(0), sender.getName());
        PlayerUtils.send(target, global_receiver_info.get(1) + " %s", type );
        PlayerUtils.send(target, global_receiver_info.get(2), text);
        // 如果提问类型是select
        if (Objects.equals(type, "select") || Objects.equals(type, "Select")){
            String[] answerText = setAnswer.answerText.split(",");
            // ClickEvent用
            TextComponent message = null;
            for (int i = 0; i <= answerText.length - 1; i++){
                TextComponent single = new TextComponent(
                        ChatUtils.translateColor("#BLUE#[#GREEN#" + answerText[i] + "#BLUE#]#RESET#  ")
                );
                ClickEvent clickEvent;
                if (answerText[i].equals(answer)){
                    clickEvent = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/answer send 答对啦！ " + sender.getName());
                } else {
                    clickEvent = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/answer send 答错了！ " + sender.getName());
                }
                single.setClickEvent(clickEvent);
                if (i == 0){
                    message = single;
                } else {
                    message.addExtra(single);
                }
            }
            PlayerUtils.send(target, "#AQUA#=====================================");
            target.spigot().sendMessage(message);
            /* wait Target Answer */
            /* to commands.impl.send */
        } else { // 提问类型不是write也不是select
            PlayerUtils.send(target, ConfigUtils.getConfig(config, "mode_err"), sender.getName());
        }
    }
}
