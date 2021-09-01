// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: response.proto

package com.mylovin.netty.rpc.pojo;

public final class ResponseProto {
  private ResponseProto() {}
  public static void registerAllExtensions(
          com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
          com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
            (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface ResponseOrBuilder extends
          // @@protoc_insertion_point(interface_extends:netty.Response)
          com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string paramReturnType = 1;</code>
     */
    java.lang.String getParamReturnType();
    /**
     * <code>string paramReturnType = 1;</code>
     */
    com.google.protobuf.ByteString
    getParamReturnTypeBytes();

    /**
     * <code>string returnValue = 2;</code>
     */
    java.lang.String getReturnValue();
    /**
     * <code>string returnValue = 2;</code>
     */
    com.google.protobuf.ByteString
    getReturnValueBytes();
  }
  /**
   * Protobuf type {@code netty.Response}
   */
  public  static final class Response extends
          com.google.protobuf.GeneratedMessageV3 implements
          // @@protoc_insertion_point(message_implements:netty.Response)
          ResponseOrBuilder {
    private static final long serialVersionUID = 0L;
    // Use Response.newBuilder() to construct.
    private Response(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private Response() {
      paramReturnType_ = "";
      returnValue_ = "";
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private Response(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
              com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              java.lang.String s = input.readStringRequireUtf8();

              paramReturnType_ = s;
              break;
            }
            case 18: {
              java.lang.String s = input.readStringRequireUtf8();

              returnValue_ = s;
              break;
            }
            default: {
              if (!parseUnknownFieldProto3(
                      input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
                e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
    getDescriptor() {
      return com.mylovin.netty.rpc.pojo.ResponseProto.internal_static_netty_Response_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
    internalGetFieldAccessorTable() {
      return com.mylovin.netty.rpc.pojo.ResponseProto.internal_static_netty_Response_fieldAccessorTable
              .ensureFieldAccessorsInitialized(
                      com.mylovin.netty.rpc.pojo.ResponseProto.Response.class, com.mylovin.netty.rpc.pojo.ResponseProto.Response.Builder.class);
    }

    public static final int PARAMRETURNTYPE_FIELD_NUMBER = 1;
    private volatile java.lang.Object paramReturnType_;
    /**
     * <code>string paramReturnType = 1;</code>
     */
    public java.lang.String getParamReturnType() {
      java.lang.Object ref = paramReturnType_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs =
                (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        paramReturnType_ = s;
        return s;
      }
    }
    /**
     * <code>string paramReturnType = 1;</code>
     */
    public com.google.protobuf.ByteString
    getParamReturnTypeBytes() {
      java.lang.Object ref = paramReturnType_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b =
                com.google.protobuf.ByteString.copyFromUtf8(
                        (java.lang.String) ref);
        paramReturnType_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int RETURNVALUE_FIELD_NUMBER = 2;
    private volatile java.lang.Object returnValue_;
    /**
     * <code>string returnValue = 2;</code>
     */
    public java.lang.String getReturnValue() {
      java.lang.Object ref = returnValue_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs =
                (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        returnValue_ = s;
        return s;
      }
    }
    /**
     * <code>string returnValue = 2;</code>
     */
    public com.google.protobuf.ByteString
    getReturnValueBytes() {
      java.lang.Object ref = returnValue_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b =
                com.google.protobuf.ByteString.copyFromUtf8(
                        (java.lang.String) ref);
        returnValue_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
            throws java.io.IOException {
      if (!getParamReturnTypeBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, paramReturnType_);
      }
      if (!getReturnValueBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, returnValue_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!getParamReturnTypeBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, paramReturnType_);
      }
      if (!getReturnValueBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, returnValue_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
        return true;
      }
      if (!(obj instanceof com.mylovin.netty.rpc.pojo.ResponseProto.Response)) {
        return super.equals(obj);
      }
      com.mylovin.netty.rpc.pojo.ResponseProto.Response other = (com.mylovin.netty.rpc.pojo.ResponseProto.Response) obj;

      boolean result = true;
      result = result && getParamReturnType()
              .equals(other.getParamReturnType());
      result = result && getReturnValue()
              .equals(other.getReturnValue());
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + PARAMRETURNTYPE_FIELD_NUMBER;
      hash = (53 * hash) + getParamReturnType().hashCode();
      hash = (37 * hash) + RETURNVALUE_FIELD_NUMBER;
      hash = (53 * hash) + getReturnValue().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.mylovin.netty.rpc.pojo.ResponseProto.Response parseFrom(
            java.nio.ByteBuffer data)
            throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.mylovin.netty.rpc.pojo.ResponseProto.Response parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.mylovin.netty.rpc.pojo.ResponseProto.Response parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.mylovin.netty.rpc.pojo.ResponseProto.Response parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.mylovin.netty.rpc.pojo.ResponseProto.Response parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.mylovin.netty.rpc.pojo.ResponseProto.Response parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.mylovin.netty.rpc.pojo.ResponseProto.Response parseFrom(java.io.InputStream input)
            throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
              .parseWithIOException(PARSER, input);
    }
    public static com.mylovin.netty.rpc.pojo.ResponseProto.Response parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
              .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.mylovin.netty.rpc.pojo.ResponseProto.Response parseDelimitedFrom(java.io.InputStream input)
            throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
              .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.mylovin.netty.rpc.pojo.ResponseProto.Response parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
              .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.mylovin.netty.rpc.pojo.ResponseProto.Response parseFrom(
            com.google.protobuf.CodedInputStream input)
            throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
              .parseWithIOException(PARSER, input);
    }
    public static com.mylovin.netty.rpc.pojo.ResponseProto.Response parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
              .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.mylovin.netty.rpc.pojo.ResponseProto.Response prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
              ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
            com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code netty.Response}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:netty.Response)
            com.mylovin.netty.rpc.pojo.ResponseProto.ResponseOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
        return com.mylovin.netty.rpc.pojo.ResponseProto.internal_static_netty_Response_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
        return com.mylovin.netty.rpc.pojo.ResponseProto.internal_static_netty_Response_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        com.mylovin.netty.rpc.pojo.ResponseProto.Response.class, com.mylovin.netty.rpc.pojo.ResponseProto.Response.Builder.class);
      }

      // Construct using com.phei.netty.rpc.pojo.ResponseProto.Response.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
              com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        paramReturnType_ = "";

        returnValue_ = "";

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
      getDescriptorForType() {
        return com.mylovin.netty.rpc.pojo.ResponseProto.internal_static_netty_Response_descriptor;
      }

      @java.lang.Override
      public com.mylovin.netty.rpc.pojo.ResponseProto.Response getDefaultInstanceForType() {
        return com.mylovin.netty.rpc.pojo.ResponseProto.Response.getDefaultInstance();
      }

      @java.lang.Override
      public com.mylovin.netty.rpc.pojo.ResponseProto.Response build() {
        com.mylovin.netty.rpc.pojo.ResponseProto.Response result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.mylovin.netty.rpc.pojo.ResponseProto.Response buildPartial() {
        com.mylovin.netty.rpc.pojo.ResponseProto.Response result = new com.mylovin.netty.rpc.pojo.ResponseProto.Response(this);
        result.paramReturnType_ = paramReturnType_;
        result.returnValue_ = returnValue_;
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return (Builder) super.clone();
      }
      @java.lang.Override
      public Builder setField(
              com.google.protobuf.Descriptors.FieldDescriptor field,
              java.lang.Object value) {
        return (Builder) super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
              com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
              com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
              com.google.protobuf.Descriptors.FieldDescriptor field,
              int index, java.lang.Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
              com.google.protobuf.Descriptors.FieldDescriptor field,
              java.lang.Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.mylovin.netty.rpc.pojo.ResponseProto.Response) {
          return mergeFrom((com.mylovin.netty.rpc.pojo.ResponseProto.Response)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.mylovin.netty.rpc.pojo.ResponseProto.Response other) {
        if (other == com.mylovin.netty.rpc.pojo.ResponseProto.Response.getDefaultInstance()) return this;
        if (!other.getParamReturnType().isEmpty()) {
          paramReturnType_ = other.paramReturnType_;
          onChanged();
        }
        if (!other.getReturnValue().isEmpty()) {
          returnValue_ = other.returnValue_;
          onChanged();
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
              com.google.protobuf.CodedInputStream input,
              com.google.protobuf.ExtensionRegistryLite extensionRegistry)
              throws java.io.IOException {
        com.mylovin.netty.rpc.pojo.ResponseProto.Response parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.mylovin.netty.rpc.pojo.ResponseProto.Response) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private java.lang.Object paramReturnType_ = "";
      /**
       * <code>string paramReturnType = 1;</code>
       */
      public java.lang.String getParamReturnType() {
        java.lang.Object ref = paramReturnType_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
                  (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          paramReturnType_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string paramReturnType = 1;</code>
       */
      public com.google.protobuf.ByteString
      getParamReturnTypeBytes() {
        java.lang.Object ref = paramReturnType_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b =
                  com.google.protobuf.ByteString.copyFromUtf8(
                          (java.lang.String) ref);
          paramReturnType_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string paramReturnType = 1;</code>
       */
      public Builder setParamReturnType(
              java.lang.String value) {
        if (value == null) {
          throw new NullPointerException();
        }

        paramReturnType_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string paramReturnType = 1;</code>
       */
      public Builder clearParamReturnType() {

        paramReturnType_ = getDefaultInstance().getParamReturnType();
        onChanged();
        return this;
      }
      /**
       * <code>string paramReturnType = 1;</code>
       */
      public Builder setParamReturnTypeBytes(
              com.google.protobuf.ByteString value) {
        if (value == null) {
          throw new NullPointerException();
        }
        checkByteStringIsUtf8(value);

        paramReturnType_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object returnValue_ = "";
      /**
       * <code>string returnValue = 2;</code>
       */
      public java.lang.String getReturnValue() {
        java.lang.Object ref = returnValue_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
                  (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          returnValue_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string returnValue = 2;</code>
       */
      public com.google.protobuf.ByteString
      getReturnValueBytes() {
        java.lang.Object ref = returnValue_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b =
                  com.google.protobuf.ByteString.copyFromUtf8(
                          (java.lang.String) ref);
          returnValue_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string returnValue = 2;</code>
       */
      public Builder setReturnValue(
              java.lang.String value) {
        if (value == null) {
          throw new NullPointerException();
        }

        returnValue_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string returnValue = 2;</code>
       */
      public Builder clearReturnValue() {

        returnValue_ = getDefaultInstance().getReturnValue();
        onChanged();
        return this;
      }
      /**
       * <code>string returnValue = 2;</code>
       */
      public Builder setReturnValueBytes(
              com.google.protobuf.ByteString value) {
        if (value == null) {
          throw new NullPointerException();
        }
        checkByteStringIsUtf8(value);

        returnValue_ = value;
        onChanged();
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
              final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFieldsProto3(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
              final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:netty.Response)
    }

    // @@protoc_insertion_point(class_scope:netty.Response)
    private static final com.mylovin.netty.rpc.pojo.ResponseProto.Response DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.mylovin.netty.rpc.pojo.ResponseProto.Response();
    }

    public static com.mylovin.netty.rpc.pojo.ResponseProto.Response getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Response>
            PARSER = new com.google.protobuf.AbstractParser<Response>() {
      @java.lang.Override
      public Response parsePartialFrom(
              com.google.protobuf.CodedInputStream input,
              com.google.protobuf.ExtensionRegistryLite extensionRegistry)
              throws com.google.protobuf.InvalidProtocolBufferException {
        return new Response(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<Response> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Response> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.mylovin.netty.rpc.pojo.ResponseProto.Response getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
          internal_static_netty_Response_descriptor;
  private static final
  com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internal_static_netty_Response_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
  getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
          descriptor;
  static {
    java.lang.String[] descriptorData = {
            "\n\016response.proto\022\005netty\032\031google/protobuf" +
                    "/any.proto\"8\n\010Response\022\027\n\017paramReturnTyp" +
                    "e\030\001 \001(\t\022\023\n\013returnValue\030\002 \001(\tB(\n\027com.phei" +
                    ".netty.rpc.pojoB\rResponseProtob\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
            new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
              public com.google.protobuf.ExtensionRegistry assignDescriptors(
                      com.google.protobuf.Descriptors.FileDescriptor root) {
                descriptor = root;
                return null;
              }
            };
    com.google.protobuf.Descriptors.FileDescriptor
            .internalBuildGeneratedFileFrom(descriptorData,
                    new com.google.protobuf.Descriptors.FileDescriptor[] {
                            com.google.protobuf.AnyProto.getDescriptor(),
                    }, assigner);
    internal_static_netty_Response_descriptor =
            getDescriptor().getMessageTypes().get(0);
    internal_static_netty_Response_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_netty_Response_descriptor,
            new java.lang.String[] { "ParamReturnType", "ReturnValue", });
    com.google.protobuf.AnyProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}