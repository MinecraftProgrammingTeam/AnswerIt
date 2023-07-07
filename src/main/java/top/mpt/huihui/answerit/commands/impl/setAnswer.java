package top.mpt.huihui.answerit.commands.impl;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import top.mpt.huihui.answerit.commands.ICommand;
import org.bukkit.command.CommandSender;
import top.mpt.huihui.answerit.utils.ChatUtils;
import top.mpt.huihui.answerit.utils.PlayerUtils;
import top.mpt.huihui.answerit.utils.i18N;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class setAnswer extends ICommand {
    public setAnswer(){
        super("setanswer", "", "no-usage");
    }

    public static String answer = "";
    public static String answerText = "";
    public static Player sender = null;
    public static Player target = null;
    // 创建token，防止玩家手动输入/answer send 答对啦！
    public static String token = "";
    public boolean onCommand(CommandSender sender, String[] args){
        // Generate token
        token = getRandomString(10);
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
            // 给发送问题方发送消息
            PlayerUtils.send(sender, i18N.getLang("select.player_send_question"));
            // 给接收问题方发送消息
            sendQuestion(((Player) sender).getPlayer(), args[2], args[3]);
        } else {
            sender.sendMessage((String) i18N.getLang("sender_err"));
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
        List<String> global_receiver_info = (List<String>) i18N.getLangList("global.receiver_info");
        PlayerUtils.send(target, "#AQUA#=====================================");
        PlayerUtils.send(target, global_receiver_info.get(0), sender.getName());
        PlayerUtils.send(target, global_receiver_info.get(1) + " %s", type );
        // text处理空格
        PlayerUtils.send(target, global_receiver_info.get(2), text.replace("-", " "));
        // 如果提问类型是select
        if (Objects.equals(type, "select") || Objects.equals(type, "Select")){
            String[] answerText = setAnswer.answerText.split(",");
            // ClickEvent用
            TextComponent message = null;


            for (int i = 0; i <= answerText.length - 1; i++){
                TextComponent single = new TextComponent(
                        ChatUtils.translateColor("#BLUE#[#GREEN#" + answerText[i].replace("-", " ") + "#BLUE#]#RESET#  ")
                );
                ClickEvent clickEvent;
                if (answerText[i].equals(answer)){
                    clickEvent = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/answer send 答对啦！ " + sender.getName() + " " + token);
                } else {
                    clickEvent = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/answer send 答错了！ " + sender.getName() + " " + token);
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
        } else { // 提问类型不是select
            PlayerUtils.send(target, i18N.getLang("mode_err"), sender.getName());
        }
    }

    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<length; i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
