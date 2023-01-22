package top.mpt.huihui.answerit.commands.impl;

import com.sun.org.apache.xpath.internal.patterns.ContextMatchStepPattern;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.w3c.dom.Text;
import top.mpt.huihui.answerit.Main;
import top.mpt.huihui.answerit.commands.ICommand;
import top.mpt.huihui.answerit.utils.ChatUtils;
import top.mpt.huihui.answerit.utils.PlayerUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class q extends ICommand {

    public q() {
        super("q", "", "提问问题");
    }

    public static Player target = null;
    public static Player sender = null;

    public boolean onCommand(CommandSender sender, String[] args) {
        /*
         * args[0] = NameFlying
         * args[1] = 灰灰怎么样?
         * args[2] = select/write
         * args[3-n] = 很好 非常好 不好 完全不好[OnlySelect]
         * 若是select就让玩家选择正确答案
         */
        // 判断玩家是不是人
        if (sender instanceof Player){
            q.sender = (Player)sender;
            // ClickEvent用
            TextComponent message = null;
            // 接收问答的人
            Player receiver = Bukkit.getPlayer(args[0]);
            // 给event传参用
            target = receiver;
            if (Objects.equals(args[2], "select") || Objects.equals(args[2], "Select")){
                // 给玩家发送消息
                PlayerUtils.send(sender, "#AQUA#请选择一个正确答案： ");
                // for循环
                for (int i = 3; i <= args.length; i++){
                    TextComponent single = new TextComponent(
                            ChatUtils.translateColor("#BLUE#[#AQUA#" + args[i] + "#BLUE]#RESET#")
                    );
                    ClickEvent clickEvent = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/answer setanswer " + target + " " + args[i]);
                    single.setClickEvent(clickEvent);
                    if (i == 3){
                        message = single;
                    } else {
                        message.addExtra(single);
                    }
                }
                sender.spigot().sendMessage(message);
            } else if (Objects.equals(args[2], "write") || Objects.equals(args[2], "Write")){
                PlayerUtils.send(sender, "");
            }
            // 发送消息
            sendQuestion(receiver, ((Player) sender).getPlayer(), args[2], args[1]);
        } else {
            sender.sendMessage(ChatUtils.translateColor("#RED#请让玩家执行指令！"));
        }
        return true;
    }

    /**
     * 给玩家发送问题
     * @param receiver 接受提问金者
     * @param sender 发出提问者
     * @param type 提问类型
     * @param text 提问文本
     */
    public void sendQuestion(Player receiver, Player sender, String type, String text){
        /* 消息格式：
         * 您收到了来自[X_huihui]的提问
         * 提问类型：Select
         * 提问内容：灰灰怎么样？
         * 回答选项/请在公屏上打出答案：[很好] [非常好] [不好] [完全不好]
         * 答对了有奖励，答错了有惩罚(?
         */
        PlayerUtils.send(receiver, "#YELLOW#您收到了来自#AQUA#[%s]#YELLOW#的提问", sender.getName());
        PlayerUtils.send(receiver, "#AQUA#提问类型： %s", type);
        PlayerUtils.send(receiver, "#GREEN#提问内容：#RESET#%s?", text);
        // 如果提问类型是select
        if (Objects.equals(type, "select") || Objects.equals(type, "Select")){
            while (true){
                if (setanswer.target == null || setanswer.target != target){
                    PlayerUtils.send(receiver, "#RED#对方还没有设置答案，请稍后。");
                } else {
                    break;
                }
            }
            String[] answerText = setanswer.answerText.split(",");
            //TODO: answer判断
            PlayerUtils.send(receiver, "#RED#请作答： #RESET#%s", answerText);
        } else if (Objects.equals(type, "write") || Objects.equals(type, "Write")){
            PlayerUtils.send(receiver, "#RED#请在输入框内输入答案，然后将交由提问者和其他玩家共同判断。");
        } else { // 提问类型不是write也不是selectR
            PlayerUtils.send(receiver, "#RED#提问类型有误，请让%s检查一下。", sender.getName());
        }
    }

    public String permission(){
        return "answer.question";
    }
}
