
package com.apocalypse.caerulaarbor.block;

import com.apocalypse.caerulaarbor.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

public class CaramelCakeBlock extends Block {
    public static final IntegerProperty BLOCKSTATE = IntegerProperty.create("blockstate", 0, 3);
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final IntegerProperty EATEN = IntegerProperty.create("eaten", 0, 3);

    public CaramelCakeBlock() {
        super(BlockBehaviour.Properties.of()
                .sound(SoundType.SCULK)
                .strength(0.5f)
                .lightLevel(s -> 0)
                .friction(0.5f)
                .speedFactor(0.8f)
                .jumpFactor(0.9f)
                .noOcclusion()
                .isRedstoneConductor((bs, br, bp) -> false)
        );
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(EATEN, 0));
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return true;
    }

    @Override
    @ParametersAreNonnullByDefault
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 0;
    }

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        if (state.getValue(BLOCKSTATE) == 1) {
            return switch (state.getValue(FACING)) {
                case NORTH -> Shapes.or(box(8, 0, 8, 15, 8, 15), box(1, 0, 1, 8, 8, 8), box(1, 0, 8, 8, 8, 15));
                case EAST -> Shapes.or(box(1, 0, 8, 8, 8, 15), box(8, 0, 1, 15, 8, 8), box(1, 0, 1, 8, 8, 8));
                case WEST -> Shapes.or(box(8, 0, 1, 15, 8, 8), box(1, 0, 8, 8, 8, 15), box(8, 0, 8, 15, 8, 15));
                default -> Shapes.or(box(1, 0, 1, 8, 8, 8), box(8, 0, 8, 15, 8, 15), box(8, 0, 1, 15, 8, 8));
            };
        }
        if (state.getValue(BLOCKSTATE) == 2) {
            return switch (state.getValue(FACING)) {
                case NORTH -> Shapes.or(box(8, 0, 8, 15, 8, 15), box(1, 0, 8, 8, 8, 15));
                case EAST -> Shapes.or(box(1, 0, 8, 8, 8, 15), box(1, 0, 1, 8, 8, 8));
                case WEST -> Shapes.or(box(8, 0, 1, 15, 8, 8), box(8, 0, 8, 15, 8, 15));
                default -> Shapes.or(box(1, 0, 1, 8, 8, 8), box(8, 0, 1, 15, 8, 8));
            };
        }
        if (state.getValue(BLOCKSTATE) == 3) {
            return switch (state.getValue(FACING)) {
                case NORTH -> box(8, 0, 8, 15, 8, 15);
                case EAST -> box(1, 0, 8, 8, 8, 15);
                case WEST -> box(8, 0, 1, 15, 8, 8);
                default -> box(1, 0, 1, 8, 8, 8);
            };
        }
        return switch (state.getValue(FACING)) {
            case NORTH ->
                    Shapes.or(box(8, 0, 8, 15, 8, 15), box(1, 0, 1, 8, 8, 8), box(1, 0, 8, 8, 8, 15), box(8, 0, 1, 15, 8, 8));
            case EAST ->
                    Shapes.or(box(1, 0, 8, 8, 8, 15), box(8, 0, 1, 15, 8, 8), box(1, 0, 1, 8, 8, 8), box(8, 0, 8, 15, 8, 15));
            case WEST ->
                    Shapes.or(box(8, 0, 1, 15, 8, 8), box(1, 0, 8, 8, 8, 15), box(8, 0, 8, 15, 8, 15), box(1, 0, 1, 8, 8, 8));
            default ->
                    Shapes.or(box(1, 0, 1, 8, 8, 8), box(8, 0, 8, 15, 8, 15), box(8, 0, 1, 15, 8, 8), box(1, 0, 8, 8, 8, 15));
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, EATEN, BLOCKSTATE);
    }

    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        return super.getStateForPlacement(context).setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(EATEN, 0);
    }

    public @NotNull BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    public @NotNull BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    private static final TagKey<Item> KNIVES_TAG = ItemTags.create(new ResourceLocation("forge:tools/knives"));

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull InteractionResult use(BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
        super.use(blockstate, world, pos, entity, hand, hit);

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        if (entity.getMainHandItem().getItem() == Blocks.AIR.asItem() && entity.getOffhandItem().isEmpty()) {
            if (blockstate.getValue(BLOCKSTATE) < 3) {
                world.setBlock(pos, world.getBlockState(pos).setValue(BLOCKSTATE, blockstate.getValue(BLOCKSTATE) + 1), 3);
            } else {
                world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
            }
            ItemHandlerHelper.giveItemToPlayer(entity, new ItemStack(ModItems.CARAMEL_CAKE_PIECE.get()));
        } else {
            if (entity.getMainHandItem().getItem() instanceof SwordItem
                    || entity.getOffhandItem().getItem() instanceof SwordItem
                    || entity.getMainHandItem().getItem() instanceof AxeItem
                    || entity.getOffhandItem().getItem() instanceof AxeItem
                    || entity.getMainHandItem().is(KNIVES_TAG)
                    || entity.getOffhandItem().is(KNIVES_TAG)
            ) {
                if (world instanceof ServerLevel server) {
                    for (int i = 0; i < 4 - blockstate.getValue(BLOCKSTATE); i++) {
                        ItemEntity entityToSpawn = new ItemEntity(server, x + 0.5, y + 0.5, z + 0.5, new ItemStack(ModItems.CARAMEL_CAKE_PIECE.get()));
                        entityToSpawn.setPickUpDelay(10);
                        server.addFreshEntity(entityToSpawn);
                    }
                }
                world.destroyBlock(pos, false);

                if (!world.isClientSide()) {
                    world.playSound(null, pos, SoundEvents.SHEEP_SHEAR, SoundSource.NEUTRAL, 1, 1);
                } else {
                    world.playLocalSound(x, y, z, SoundEvents.SHEEP_SHEAR, SoundSource.NEUTRAL, 1, 1, false);
                }
            } else {
                return InteractionResult.PASS;
            }
        }
        return InteractionResult.SUCCESS;
    }
}
