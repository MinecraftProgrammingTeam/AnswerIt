package top.mpt.huihui.answerit.commands.impl;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.mpt.huihui.answerit.commands.ICommand;
import top.mpt.huihui.answerit.prize.prize;
import top.mpt.huihui.answerit.utils.ChatUtils;
import top.mpt.huihui.answerit.utils.PlayerUtils;
import top.mpt.huihui.answerit.utils.i18N;

import static top.mpt.huihui.answerit.Main.*;

import java.util.List;
import java.util.Objects;

public class q extends ICommand {

    public q() {
        super("q", "", "/answer q [PlayerName] [Question] [select/wrtite] [answer] [answer] *n");
        // 传入服务器全体玩家List
        setListParams(Online_Players);
    }

    public static Player target = null;
    public static Player sender = null;

    /* from PlayerExecuteCommand */
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
            // 防止index out of range
            if (args.length < 3){
                PlayerUtils.send(sender, i18N.getLang("global.command_err_format"));
                PlayerUtils.send(sender, "/answer q [PlayerName] [Question] [Type] [answer*n if select]");
                return true;
            }
            // 接收问答的人
            // 给event传参用
            target = Bukkit.getPlayer(args[0]);
            // 判断target
            if (target == null){
                PlayerUtils.send(sender, i18N.getLang("player_err"));
                return true;
            }
            // 如果该玩家正在被提问或正在对别的玩家进行提问
            if (playersOnQuestioning.contains(target) || playersOnQuestioning.contains(q.sender)){
                PlayerUtils.send(q.sender, i18N.getLang("please_wait_err"));
                return true;
            }
            // 默认玩家已经开始了询问，不管是select还是write
            playersOnQuestioning.add(q.sender);
            playersOnQuestioning.add(target);
            if (Objects.equals(args[2], "select") || Objects.equals(args[2], "Select")){
                // 给玩家发送消息
                PlayerUtils.send(sender, i18N.getLang("select.player_choose_answer"));
                // 定义回答文本(仅程序，不影响玩家)
                StringBuilder answerText = new StringBuilder();
                // 判断回答文本
                for (int a = 3; a <= args.length - 1; a++){
                    if (a == args.length - 1){
                        answerText.append(args[a]);
                    } else {
                        answerText.append(args[a]).append(",");
                    }
                }
                // for循环
                for (int i = 3; i <= args.length - 1; i++){
                    TextComponent single = new TextComponent(
                            ChatUtils.translateColor("#BLUE#[#GREEN#" + args[i].replace("-", " ") + "#BLUE#]#RESET#  ")
                    );
                    ClickEvent clickEvent = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/answer setanswer " + target.getName() + " " + args[i] + " Select " + args[1] + " " + answerText);
                    single.setClickEvent(clickEvent);
                    // 判断TextComponent应该怎么叠
                    if (i == 3){
                        message = single;
                    } else {
                        message.addExtra(single);
                    }
                }
                // 可以给予奖励
                prize.canPrize = true;
                sender.spigot().sendMessage(message);
                /* start select process */
                /* wait Sender Select Right Answer */
                /* then to commands.impl.setAnswer */

            } else if (Objects.equals(args[2], "write") || Objects.equals(args[2], "Write")){
                List<String> global_receiver_info = (List<String>) i18N.getLangList("global.receiver_info");
                List<String> global_broadcast_info = (List<String>) i18N.getLangList("global.broadcast_info");
                PlayerUtils.send(target, "#AQUA#=====================================");
                PlayerUtils.send(target, global_receiver_info.get(0), sender.getName());
                PlayerUtils.send(target, global_receiver_info.get(1) + " Write");
                PlayerUtils.send(target, global_receiver_info.get(2), args[1].replace("-", " "));
                PlayerUtils.send(target, global_receiver_info.get(3));

                ChatUtils.broadcast(global_broadcast_info.get(0),
                        target.getName(), sender.getName());
                PlayerUtils.send(target, "#AQUA#=====================================");
                ChatUtils.broadcast(global_broadcast_info.get(1), args[1].replace("-", " "));
                ChatUtils.broadcast(global_broadcast_info.get(2), target.getName());
                isCheckChat = true;
                // 假设玩家会答对，timer那里可以直接execute
                prize.setPrizePlayer(target);
                prize.setTargetPlayer((Player) sender);
                /* start write process */
                /* wait Player Answer Question */
                /* then to listener.PlayerChat */
            }

        } else {
            // 如果都不是，就给玩家发送错误消息
            sender.sendMessage((String) i18N.getLang("sender_err"));
        }
        return true;
    }



    public String permission(){
        return "answer.question";
    }
}
