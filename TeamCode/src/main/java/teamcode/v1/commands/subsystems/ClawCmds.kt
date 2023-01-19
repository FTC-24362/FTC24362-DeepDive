package teamcode.v1.commands.subsystems

import com.asiankoala.koawalib.command.commands.InstantCmd
import teamcode.v1.constants.ClawConstants
import org.firstinspires.ftc.teamcode.koawalib.subsystems.Claw

class ClawCmds {
    open class ClawCmd(claw: Claw, pos: Double) : InstantCmd({ claw.setPos(pos) }, claw)

    class ClawCloseCmd(claw: Claw) : ClawCmd(claw, ClawConstants.closePos)
    class ClawOpenCmd(claw: Claw) : ClawCmd(claw, ClawConstants.openPos)
}