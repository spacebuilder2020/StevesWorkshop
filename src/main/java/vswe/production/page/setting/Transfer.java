package vswe.production.page.setting;


import net.minecraft.item.ItemStack;
import vswe.production.item.Upgrade;
import vswe.production.tileentity.TileEntityTable;

public class Transfer {
    private boolean enabled;
    private boolean isInput;
    private boolean auto;
    private ItemSetting[] items;
    private boolean useWhiteList;


    public Transfer(boolean isInput) {
        this.isInput = isInput;
        items = new ItemSetting[ItemSetting.ITEM_COUNT];
        for (int i = 0; i < items.length; i++) {
            items[i] = new ItemSetting(i);
        }
    }

    public boolean isInput() {
        return isInput;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAuto() {
        return auto;
    }

    public void setAuto(boolean auto) {
        this.auto = auto;
    }

    public boolean hasWhiteList() {
        return useWhiteList;
    }

    public void setUseWhiteList(boolean useWhiteList) {
        this.useWhiteList = useWhiteList;
    }

    public ItemSetting getItem(int id) {
        return items[id];
    }

    public boolean isValid(TileEntityTable table, ItemStack item) {
        if (item == null || !table.getUpgradePage().hasGlobalUpgrade(Upgrade.FILTER)) {
            return true;
        }

        for (ItemSetting itemSetting : items) {
            ItemStack filterItem = itemSetting.getItem();
            if (filterItem != null) {
                boolean match = itemSetting.getMode().isMatch(item, filterItem);

                if (match) {
                    return useWhiteList;
                }
            }
        }

        return !useWhiteList;
    }

    public boolean hasFilter(TileEntityTable table) {
        if (table.getUpgradePage().hasGlobalUpgrade(Upgrade.FILTER)) {
            for (ItemSetting item : items) {
                if (item.getItem() != null) {
                    return true;
                }
            }
        }

        return false;
    }
}
