package com.mooviies.maplelib.item;

import com.mooviies.maplelib.MapleMod;
import com.mooviies.maplelib.MapleModDescriptor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;

public class MItemFood extends ItemFood {

    protected String name;
    protected MapleModDescriptor modDescriptor;
    private String oreName;
    private static ArrayList<MItemFood> items = new ArrayList<>();

    public MItemFood(MapleModDescriptor modDescriptor, String name, String oreName, int amount, float saturation, boolean isWolfFood) {
        super(amount, saturation, isWolfFood);
        initialize(modDescriptor, name, oreName);
    }

    public static void register(IForgeRegistry<Item> registry) {
        for(MItemFood item : items)
            registry.register(item);
    }

    public static void registerItemModel() {
        for(MItemFood item : items)
            MapleMod.proxy.registerItemRenderer(item.modDescriptor, item, 0, item.name);
    }

    public static void initOreDict() {
        for(MItemFood item : items)
            OreDictionary.registerOre(item.oreName, item);
    }

    @Override
    public MItemFood setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }

    private void initialize(MapleModDescriptor modDescriptor, String name, String oreName)
    {
        this.name = name;
        this.oreName = oreName;

        setUnlocalizedName(name);
        setRegistryName(name);

        items.add(this);

        this.modDescriptor = modDescriptor;
        setCreativeTab(modDescriptor.getCreativeTab());
    }
}
