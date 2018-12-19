/**
 * Copyright © 2018 TaoYu (tracy5546@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ljb.auto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * 验证码组件参数
 *
 * @author TaoYu
 */
@ConfigurationProperties(
        prefix = "kaptcha"
)
public class KaptchaProperties {
    private Integer width = 200;
    private Integer height = 50;
    @NestedConfigurationProperty
    private KaptchaProperties.Content content = new KaptchaProperties.Content();
    @NestedConfigurationProperty
    private KaptchaProperties.BackgroundColor backgroundColor = new KaptchaProperties.BackgroundColor();
    @NestedConfigurationProperty
    private KaptchaProperties.Font font = new KaptchaProperties.Font();
    @NestedConfigurationProperty
    private KaptchaProperties.Border border = new KaptchaProperties.Border();

    public KaptchaProperties() {
    }

    public Integer getWidth() {
        return this.width;
    }

    public Integer getHeight() {
        return this.height;
    }

    public KaptchaProperties.Content getContent() {
        return this.content;
    }

    public KaptchaProperties.BackgroundColor getBackgroundColor() {
        return this.backgroundColor;
    }

    public KaptchaProperties.Font getFont() {
        return this.font;
    }

    public KaptchaProperties.Border getBorder() {
        return this.border;
    }

    public void setWidth(final Integer width) {
        this.width = width;
    }

    public void setHeight(final Integer height) {
        this.height = height;
    }

    public void setContent(final KaptchaProperties.Content content) {
        this.content = content;
    }

    public void setBackgroundColor(final KaptchaProperties.BackgroundColor backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setFont(final KaptchaProperties.Font font) {
        this.font = font;
    }

    public void setBorder(final KaptchaProperties.Border border) {
        this.border = border;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof KaptchaProperties)) {
            return false;
        } else {
            KaptchaProperties other = (KaptchaProperties)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$width = this.getWidth();
                Object other$width = other.getWidth();
                if (this$width == null) {
                    if (other$width != null) {
                        return false;
                    }
                } else if (!this$width.equals(other$width)) {
                    return false;
                }

                Object this$height = this.getHeight();
                Object other$height = other.getHeight();
                if (this$height == null) {
                    if (other$height != null) {
                        return false;
                    }
                } else if (!this$height.equals(other$height)) {
                    return false;
                }

                Object this$content = this.getContent();
                Object other$content = other.getContent();
                if (this$content == null) {
                    if (other$content != null) {
                        return false;
                    }
                } else if (!this$content.equals(other$content)) {
                    return false;
                }

                label62: {
                    Object this$backgroundColor = this.getBackgroundColor();
                    Object other$backgroundColor = other.getBackgroundColor();
                    if (this$backgroundColor == null) {
                        if (other$backgroundColor == null) {
                            break label62;
                        }
                    } else if (this$backgroundColor.equals(other$backgroundColor)) {
                        break label62;
                    }

                    return false;
                }

                label55: {
                    Object this$font = this.getFont();
                    Object other$font = other.getFont();
                    if (this$font == null) {
                        if (other$font == null) {
                            break label55;
                        }
                    } else if (this$font.equals(other$font)) {
                        break label55;
                    }

                    return false;
                }

                Object this$border = this.getBorder();
                Object other$border = other.getBorder();
                if (this$border == null) {
                    if (other$border != null) {
                        return false;
                    }
                } else if (!this$border.equals(other$border)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof KaptchaProperties;
    }

    public int hashCode() {
        int result = 1;
        Object $width = this.getWidth();
        result = result * 59 + ($width == null ? 43 : $width.hashCode());
        Object $height = this.getHeight();
        result = result * 59 + ($height == null ? 43 : $height.hashCode());
        Object $content = this.getContent();
        result = result * 59 + ($content == null ? 43 : $content.hashCode());
        Object $backgroundColor = this.getBackgroundColor();
        result = result * 59 + ($backgroundColor == null ? 43 : $backgroundColor.hashCode());
        Object $font = this.getFont();
        result = result * 59 + ($font == null ? 43 : $font.hashCode());
        Object $border = this.getBorder();
        result = result * 59 + ($border == null ? 43 : $border.hashCode());
        return result;
    }

    public String toString() {
        return "KaptchaProperties(width=" + this.getWidth() + ", height=" + this.getHeight() + ", content=" + this.getContent() + ", backgroundColor=" + this.getBackgroundColor() + ", font=" + this.getFont() + ", border=" + this.getBorder() + ")";
    }

    static class Font {
        private String name = "Arial";
        private String color = "black";
        private Integer size = 40;

        public Font() {
        }

        public String getName() {
            return this.name;
        }

        public String getColor() {
            return this.color;
        }

        public Integer getSize() {
            return this.size;
        }

        public void setName(final String name) {
            this.name = name;
        }

        public void setColor(final String color) {
            this.color = color;
        }

        public void setSize(final Integer size) {
            this.size = size;
        }

        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof KaptchaProperties.Font)) {
                return false;
            } else {
                KaptchaProperties.Font other = (KaptchaProperties.Font)o;
                if (!other.canEqual(this)) {
                    return false;
                } else {
                    label47: {
                        Object this$name = this.getName();
                        Object other$name = other.getName();
                        if (this$name == null) {
                            if (other$name == null) {
                                break label47;
                            }
                        } else if (this$name.equals(other$name)) {
                            break label47;
                        }

                        return false;
                    }

                    Object this$color = this.getColor();
                    Object other$color = other.getColor();
                    if (this$color == null) {
                        if (other$color != null) {
                            return false;
                        }
                    } else if (!this$color.equals(other$color)) {
                        return false;
                    }

                    Object this$size = this.getSize();
                    Object other$size = other.getSize();
                    if (this$size == null) {
                        if (other$size != null) {
                            return false;
                        }
                    } else if (!this$size.equals(other$size)) {
                        return false;
                    }

                    return true;
                }
            }
        }

        protected boolean canEqual(final Object other) {
            return other instanceof KaptchaProperties.Font;
        }

        public int hashCode() {
            int result = 1;
            Object $name = this.getName();
            result = result * 59 + ($name == null ? 43 : $name.hashCode());
            Object $color = this.getColor();
            result = result * 59 + ($color == null ? 43 : $color.hashCode());
            Object $size = this.getSize();
            result = result * 59 + ($size == null ? 43 : $size.hashCode());
            return result;
        }

        public String toString() {
            return "KaptchaProperties.Font(name=" + this.getName() + ", color=" + this.getColor() + ", size=" + this.getSize() + ")";
        }
    }

    static class Border {
        private Boolean enabled = true;
        private String color = "black";
        private Integer thickness = 1;

        public Border() {
        }

        public Boolean getEnabled() {
            return this.enabled;
        }

        public String getColor() {
            return this.color;
        }

        public Integer getThickness() {
            return this.thickness;
        }

        public void setEnabled(final Boolean enabled) {
            this.enabled = enabled;
        }

        public void setColor(final String color) {
            this.color = color;
        }

        public void setThickness(final Integer thickness) {
            this.thickness = thickness;
        }

        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof KaptchaProperties.Border)) {
                return false;
            } else {
                KaptchaProperties.Border other = (KaptchaProperties.Border)o;
                if (!other.canEqual(this)) {
                    return false;
                } else {
                    label47: {
                        Object this$enabled = this.getEnabled();
                        Object other$enabled = other.getEnabled();
                        if (this$enabled == null) {
                            if (other$enabled == null) {
                                break label47;
                            }
                        } else if (this$enabled.equals(other$enabled)) {
                            break label47;
                        }

                        return false;
                    }

                    Object this$color = this.getColor();
                    Object other$color = other.getColor();
                    if (this$color == null) {
                        if (other$color != null) {
                            return false;
                        }
                    } else if (!this$color.equals(other$color)) {
                        return false;
                    }

                    Object this$thickness = this.getThickness();
                    Object other$thickness = other.getThickness();
                    if (this$thickness == null) {
                        if (other$thickness != null) {
                            return false;
                        }
                    } else if (!this$thickness.equals(other$thickness)) {
                        return false;
                    }

                    return true;
                }
            }
        }

        protected boolean canEqual(final Object other) {
            return other instanceof KaptchaProperties.Border;
        }

        public int hashCode() {
            int result = 1;
            Object $enabled = this.getEnabled();
            result = result * 59 + ($enabled == null ? 43 : $enabled.hashCode());
            Object $color = this.getColor();
            result = result * 59 + ($color == null ? 43 : $color.hashCode());
            Object $thickness = this.getThickness();
            result = result * 59 + ($thickness == null ? 43 : $thickness.hashCode());
            return result;
        }

        public String toString() {
            return "KaptchaProperties.Border(enabled=" + this.getEnabled() + ", color=" + this.getColor() + ", thickness=" + this.getThickness() + ")";
        }
    }

    static class Content {
        private String source = "abcdefghjklmnopqrstuvwxyz23456789";
        private Integer length = 4;
        private Integer space = 2;

        public Content() {
        }

        public String getSource() {
            return this.source;
        }

        public Integer getLength() {
            return this.length;
        }

        public Integer getSpace() {
            return this.space;
        }

        public void setSource(final String source) {
            this.source = source;
        }

        public void setLength(final Integer length) {
            this.length = length;
        }

        public void setSpace(final Integer space) {
            this.space = space;
        }

        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof KaptchaProperties.Content)) {
                return false;
            } else {
                KaptchaProperties.Content other = (KaptchaProperties.Content)o;
                if (!other.canEqual(this)) {
                    return false;
                } else {
                    label47: {
                        Object this$source = this.getSource();
                        Object other$source = other.getSource();
                        if (this$source == null) {
                            if (other$source == null) {
                                break label47;
                            }
                        } else if (this$source.equals(other$source)) {
                            break label47;
                        }

                        return false;
                    }

                    Object this$length = this.getLength();
                    Object other$length = other.getLength();
                    if (this$length == null) {
                        if (other$length != null) {
                            return false;
                        }
                    } else if (!this$length.equals(other$length)) {
                        return false;
                    }

                    Object this$space = this.getSpace();
                    Object other$space = other.getSpace();
                    if (this$space == null) {
                        if (other$space != null) {
                            return false;
                        }
                    } else if (!this$space.equals(other$space)) {
                        return false;
                    }

                    return true;
                }
            }
        }

        protected boolean canEqual(final Object other) {
            return other instanceof KaptchaProperties.Content;
        }

        public int hashCode() {
            int result = 1;
            Object $source = this.getSource();
            result = result * 59 + ($source == null ? 43 : $source.hashCode());
            Object $length = this.getLength();
            result = result * 59 + ($length == null ? 43 : $length.hashCode());
            Object $space = this.getSpace();
            result = result * 59 + ($space == null ? 43 : $space.hashCode());
            return result;
        }

        public String toString() {
            return "KaptchaProperties.Content(source=" + this.getSource() + ", length=" + this.getLength() + ", space=" + this.getSpace() + ")";
        }
    }

    static class BackgroundColor {
        private String from = "lightGray";
        private String to = "white";

        public BackgroundColor() {
        }

        public String getFrom() {
            return this.from;
        }

        public String getTo() {
            return this.to;
        }

        public void setFrom(final String from) {
            this.from = from;
        }

        public void setTo(final String to) {
            this.to = to;
        }

        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof KaptchaProperties.BackgroundColor)) {
                return false;
            } else {
                KaptchaProperties.BackgroundColor other = (KaptchaProperties.BackgroundColor)o;
                if (!other.canEqual(this)) {
                    return false;
                } else {
                    Object this$from = this.getFrom();
                    Object other$from = other.getFrom();
                    if (this$from == null) {
                        if (other$from != null) {
                            return false;
                        }
                    } else if (!this$from.equals(other$from)) {
                        return false;
                    }

                    Object this$to = this.getTo();
                    Object other$to = other.getTo();
                    if (this$to == null) {
                        if (other$to != null) {
                            return false;
                        }
                    } else if (!this$to.equals(other$to)) {
                        return false;
                    }

                    return true;
                }
            }
        }

        protected boolean canEqual(final Object other) {
            return other instanceof KaptchaProperties.BackgroundColor;
        }

        public int hashCode() {
            int result = 1;
            Object $from = this.getFrom();
            result = result * 59 + ($from == null ? 43 : $from.hashCode());
            Object $to = this.getTo();
            result = result * 59 + ($to == null ? 43 : $to.hashCode());
            return result;
        }

        public String toString() {
            return "KaptchaProperties.BackgroundColor(from=" + this.getFrom() + ", to=" + this.getTo() + ")";
        }
    }
}
