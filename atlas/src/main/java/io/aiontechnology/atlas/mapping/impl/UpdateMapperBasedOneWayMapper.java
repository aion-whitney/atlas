package io.aiontechnology.atlas.mapping.impl;

import io.aiontechnology.atlas.mapping.OneWayMapper;
import io.aiontechnology.atlas.mapping.OneWayUpdateMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

/**
 * A {@link OneWayMapper} that is based on a corresponding {@link OneWayUpdateMapper}.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@Slf4j
@RequiredArgsConstructor
public class UpdateMapperBasedOneWayMapper<FROM, TO> implements OneWayMapper<FROM, TO> {

    /** The {@link OneWayUpdateMapper} to use for mapping. */
    private final OneWayUpdateMapper<FROM, TO> mapper;

    /** The Class of the TO type */
    private final Class<TO> toClass;

    /**
     * Map the from object to an object of type TO. The TO object will be created by the default constructor of
     * {@link #toClass}.
     *
     * @param from The object to map from.
     * @return The resulting mapped object.
     */
    @Override
    public Optional<TO> map(FROM from) {
        return Optional.ofNullable(from)
                .flatMap(f -> {
                    try {
                        Constructor<TO> ctor = toClass.getConstructor();
                        return mapper.map(f, ctor.newInstance());
                    } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                            InvocationTargetException e) {
                        log.error("Can't create instance of class: {}", toClass);
                        throw new RuntimeException("Invalid class provided", e);
                    }
                });
    }

}
