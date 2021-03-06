/*
 * Copyright (c) 2015, 2016, 2017 JTS-Team authors and/or its affiliates. All rights reserved.
 *
 * This file is part of JTS-V3 Project.
 *
 * JTS-V3 Project is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JTS-V3 Project is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JTS-V3 Project.  If not, see <http://www.gnu.org/licenses/>.
 */

package ru.jts_dev.gameserver.parser.html;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.jts_dev.gameserver.config.CacheConfig;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Java-man
 * @since 04.01.2016
 */
@EnableConfigurationProperties(HtmlRepositoryConfig.class)
@SpringJUnitConfig(classes = {HtmlRepository.class, CacheConfig.class})
@TestPropertySource(properties = {"gameserver.html.repository.type=lazy"})
public class HtmlRepositoryTest {
    @Autowired
    private HtmlRepository htmlRepository;

    @Test
    public void testGetHtml() throws Exception {
        String content = htmlRepository.getHtml(Locale.ENGLISH, "abel001.htm");
        assertThat(content).hasSize(512);

        content = htmlRepository.getHtml(Locale.ENGLISH, "abel002.htm");
        assertThat(content).hasSize(367);
    }
}
