package vswe.production.page;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import vswe.production.gui.GuiBase;
import vswe.production.gui.container.slot.SlotBase;
import vswe.production.tileentity.TileEntityTable;


public class Page {
    private String name;
    protected TileEntityTable table;


    public Page(TileEntityTable table, String name) {
        this.table = table;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int createSlots(int id) {
        return id;
    }

    protected void addSlot(SlotBase slot) {
        table.addSlot(slot);
    }

    @SideOnly(Side.CLIENT)
    public void draw(GuiBase gui, int mX, int mY) {
        gui.drawString(name, 8, 6, 0x404040);
    }
}
