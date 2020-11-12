package yamahari.tagyoureit.eventhandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderNameplateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yamahari.tagyoureit.util.Constants;

@Mod.EventBusSubscriber(value = {Dist.CLIENT}, modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class RenderNameplateEventHandler {
    @SubscribeEvent
    public static void onRenderNameplateEvent(final RenderNameplateEvent event) {
        final Entity entity = event.getEntity();
        if (entity instanceof PlayerEntity) {
            final PlayerEntity playerEntity = (PlayerEntity) entity;
            final ItemStack mainItemStack = playerEntity.getHeldItemMainhand();
            final ItemStack offItemStack = playerEntity.getHeldItemOffhand();
            if (!mainItemStack.isEmpty()) {
                final Item item = mainItemStack.getItem();
                if (item == Items.NAME_TAG && mainItemStack.hasDisplayName()) {
                    event.setContent(mainItemStack.getDisplayName());
                    return;
                }
            }
            if (!offItemStack.isEmpty()) {
                final Item item = offItemStack.getItem();
                if (item == Items.NAME_TAG && offItemStack.hasDisplayName()) {
                    event.setContent(offItemStack.getDisplayName());
                }
            }
        }
    }
}
